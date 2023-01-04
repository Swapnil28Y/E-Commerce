package com.product.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.product.exceptions.CartException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.exceptions.ProductException;
import com.product.model.Cart;
import com.product.model.Customer;
import com.product.model.Product;
import com.product.model.UserSession;
import com.product.repository.CartDao;
import com.product.repository.CustomerDao;
import com.product.repository.ProductDao;
import com.product.repository.SessionDao;

public class CartServiceImp implements CartService {

	@Autowired
	private CartDao caDao;

	@Autowired
	private SessionDao sDao;

	@Autowired
	private ProductDao pDao;

	@Autowired
	private CustomerDao cDao;

	@Override
	public Cart addProductToCart(Integer productId, int quantity, String key)
			throws CartException, LoginException, ProductException, CustomerException {
		UserSession user = sDao.findByUuid(key);
		if(user == null) {
			throw new LoginException("please login first");
		}
		Optional<Customer>  cus=cDao.findById(user.getUserid());
		if(cus.isPresent()) {
			Customer currentcutomer=cus.get();
			Optional<Product> optproduct=pDao.findById(productId);
			
			if(optproduct.isEmpty()) {
				throw new ProductException("Product is not found");
			}
			Product currentproduct=optproduct.get();
		      if(currentproduct.getQuantity()<quantity) {
		    	  throw new ProductException("Product quantity not available");
		      }
		      Cart customercart=caDao.findByCustomer(currentcutomer);
				if(customercart==null) {
					customercart=new Cart();
					customercart.setCustomer(currentcutomer);
					List<Product> list=customercart.getProducts();
				currentproduct.setQuantity(currentproduct.getQuantity()-quantity);
				list.add(currentproduct);
				pDao.save(currentproduct);
				caDao.save(customercart);
				return customercart;
				}
				else {
					List<Product> list=customercart.getProducts();
				currentproduct.setQuantity(currentproduct.getQuantity()-quantity);
				list.add(currentproduct);
				caDao.save(customercart);
				pDao.save(currentproduct);
				return customercart;
				
				}
			}else {
				throw new CustomerException("customer is not there");
			}
	}

	@Override
	public List<Product> removeProductFromCart(Integer productId, String key)
			throws CartException, ProductException, LoginException {
		UserSession user = sDao.findByUuid(key);
		if(user==null) {
			throw new LoginException("You have not logged in");
		}
		Optional<Customer> cus = cDao.findById(user.getUserid());
		Customer currentcutomer=cus.get();
	Optional<Product> optproduct=pDao.findById(productId);
	if(optproduct.isEmpty()) {
		throw new ProductException("product is not available");
	}
	Product currentproduct=optproduct.get();
	Cart customerCart=caDao.findByCustomer(currentcutomer);
	if(customerCart!=null) {
		List<Product> list=customerCart.getProducts();
		boolean flag=false;
		for(int i=0;i<list.size();i++) {
			Product product=list.get(i);
			if(product.getProductid()==productId) {
				pDao.deleteById(productId);
				flag=true;
				currentproduct.setQuantity(currentproduct.getQuantity()+product.getQuantity());
				pDao.save(currentproduct);
				list.remove(i);
				break;
			}
		}
		if(!flag) {
			throw new ProductException("there is no product found with"+productId);
			
		}
		customerCart.setProducts(list);
		caDao.save(customerCart);
		return list;
	}
	else {
		throw new ProductException("Their nothing in cart");
	}
	}

	@Override
	public List<Product> updateProductQuantity(Integer productId, Integer quantity, String key)
			throws CartException, LoginException, ProductException {
		UserSession user =sDao.findByUuid(key);
		if(user==null) 
		{
			throw new LoginException("You have not logged in");
		}
		Optional<Customer> cus = cDao.findById(user.getUserid());
		Customer currentcutomer=cus.get();
	Optional<Product> optproduct=pDao.findById(productId);
	if(optproduct.isEmpty())
	{
		throw new ProductException("product is not available");
	}
	Product currentproduct=optproduct.get();
	if(currentproduct.getQuantity() < quantity) 
	{
		throw new ProductException("Product Out of stock") ;
	}
	
	Cart customerCart = caDao.findByCustomer(currentcutomer);
	if(customerCart!=null) 
	{
		List<Product> list=customerCart.getProducts();
		boolean flag=false;
		List<Product> updateList=new ArrayList<>();
		for(Product productdto:list)
		{
			if(productdto.getProductid()==productId)
			{
				flag=true;
				currentproduct.setQuantity(currentproduct.getQuantity()-quantity);
				pDao.save(currentproduct);
				productdto.setQuantity(productdto.getQuantity()+ quantity);
				Product pr=pDao.save(productdto);
				updateList.add(pr);
				break;
			}
		}
	
		if(!flag) {
			throw new ProductException("You have no product with productid"+productId);
		}
	       return updateList;
	}
	       
	       
	       else {
		throw new ProductException("You have no product in cart");
	}
	}

	@Override
	public Cart removeAllProducts(String key) throws CartException, LoginException {
		UserSession user = sDao.findByUuid(key);
		if(user==null) 
		{
			throw new LoginException("You have not logged in");
		}
		Optional<Customer> cus = cDao.findById(user.getUserid());
		Customer currentcutomer=cus.get();
		Cart customerCart = caDao.findByCustomer(currentcutomer);
		List<Product> list=customerCart.getProducts();
		if(list.size()>0) {
			for(Product productdto:list) {
				Optional<Product> opt=pDao.findById(productdto.getProductid());
				Product currentproduct=opt.get();
				currentproduct.setQuantity(currentproduct.getQuantity()+productdto.getQuantity());
				pDao.delete(productdto);
				pDao.save(currentproduct);
			}
		}
		customerCart.setProducts(new ArrayList<>());
		return caDao.save(customerCart);
	}

	@Override
	public List<Product> viewAllProducts(String key) throws CartException, LoginException {
		UserSession user = sDao.findByUuid(key);
		if(user==null) 
		{
			throw new LoginException("You have not logged in");
		}
		Optional<Customer> cus = cDao.findById(user.getUserid());
		Customer currentcutomer=cus.get();
		Cart customercart=caDao.findByCustomer(currentcutomer);
		if(currentcutomer==null) {
			throw new CartException("cart is empty");
		}
		return customercart.getProducts();
	}

}
