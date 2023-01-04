package com.product.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.DTO.AddressDTO;
import com.product.exceptions.CartException;
import com.product.exceptions.LoginException;
import com.product.exceptions.OrderException;
import com.product.exceptions.ProductException;
import com.product.model.Address;
import com.product.model.Cart;
import com.product.model.Customer;
import com.product.model.Orders;
import com.product.model.Product;
import com.product.model.UserSession;
import com.product.repository.AddressDao;
import com.product.repository.CartDao;
import com.product.repository.CustomerDao;
import com.product.repository.OrderDao;
import com.product.repository.SessionDao;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private OrderDao oDao;

	@Autowired
	private SessionDao sDao;

	@Autowired
	private CustomerDao cDao;

	@Autowired
	private CartDao caDao;

	@Autowired
	private AddressDao aDao;

	@Override
	public Orders registerOrder(Orders order, String key)
			throws OrderException, CartException, LoginException, ProductException {
		UserSession user = sDao.findByUuid(key);

		if (user != null) {
			Integer customerId = user.getUserid();

			Optional<Customer> ourCustomer = cDao.findById(customerId);

			List<Address> ad = ourCustomer.get().getAddress();

			Address addr = ad.get(0);
			Orders currOrder = new Orders();

			currOrder.setOrderDate(order.getOrderDate());
			AddressDTO adto = new AddressDTO(addr.getStreetno(), addr.getBuildingname(), addr.getCity(), addr.getState(),
					addr.getCountry(), addr.getPincode());
			currOrder.setAddressdto(adto);

			currOrder.setCustomer(ourCustomer.get());
			currOrder.setOrderStatus("Order confirmed");

			Cart ca = caDao.findByCustomer(ourCustomer.get());

			List<Product> products = ca.getProducts();
			if (products.size() == 0) {
				throw new ProductException("Products not prsent");
			}

			List<Product> productList = new ArrayList<>();

			Double total = 0.0;

			for (Product proDto : products) {

				productList.add(proDto);

				total += (proDto.getProductprice() * proDto.getQuantity());

			}

			currOrder.setTotal(total);
			currOrder.setList(productList);

			Cart customerCart = caDao.findByCustomer(ourCustomer.get());

			customerCart.setProducts(new ArrayList<>());

			caDao.save(customerCart);

			return oDao.save(currOrder);

		} else {
			throw new LoginException("Login first");
		}
	}

	@Override
	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException {
		UserSession user=sDao.findByUuid(key);
		if(user==null) {
			throw new LoginException("User not found "+key);
		}else {
			Optional<Orders> o=oDao.findById(order.getOrderid());
			if(o.isPresent()) {
				o.get().setOrderDate(order.getOrderDate());
				Orders ord=o.get();
				Orders or=oDao.save(ord);
				return or;
			}else {
				throw new OrderException("Order not found");
			}
			
		}
	}

	@Override
	public Orders removeOrder(Integer oriderId, String key) throws OrderException, LoginException {
		UserSession user=sDao.findByUuid(key);
		if(user==null) {
			throw new LoginException("User not found "+key);
		}else {
		
		Orders existingorder=oDao.findById(oriderId).orElseThrow(()-> new OrderException("Order does not exist with this id"));
		oDao.delete(existingorder);
		return existingorder;
		}
	}

	@Override
	public Orders viewOrder(Integer orderId, String key) throws OrderException, LoginException {
		UserSession user=sDao.findByUuid(key);
		if(user==null) {
			throw new LoginException("User not found "+key);
		}else {
			Optional<Orders> optorder=oDao.findById(orderId);
			Orders order=optorder.get();
			return order;
		}
	}

}
