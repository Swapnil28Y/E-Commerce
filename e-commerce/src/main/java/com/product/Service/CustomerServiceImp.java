package com.product.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.product.exceptions.AddressException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.model.Address;
import com.product.model.Customer;
import com.product.model.UserSession;
import com.product.repository.AddressDao;
import com.product.repository.CustomerDao;
import com.product.repository.SessionDao;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerDao cdao;
	@Autowired
	private SessionDao sdao;
	@Autowired
	private AddressDao adao;

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException, AddressException {
		Customer custom = cdao.findByMobilenumber(customer.getMobilenumber());
		if (custom == null) {
			if (custom.getAddress() != null) {
				List<Address> adds = customer.getAddress();
				for (Address i : adds) {
					i.setCustomer(customer);
					adao.save(i);
				}

			} else {
				throw new AddressException("Not found");
			}

			Customer cc = cdao.save(custom);
			return cc;
		} else {
			throw new CustomerException("Customer Already registered...");
		}
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException, LoginException {
		UserSession session = sdao.findByUuid(key);
		if (session == null) {
			throw new LoginException("You are not logged in");
		} else {
			if (session.getUserid() == customer.getCustomerid()) {
				List<Address> adds = customer.getAddress();
				if (adds == null) {
					throw new CustomerException("Address not found");
				} else {
					for (Address i : adds) {
						i.setCustomer(customer);
						adao.save(i);
					}
					Customer cc = cdao.save(customer);
					return cc;
				}

			} else {
				throw new CustomerException("Access Denied");
			}
		}
	}

	@Override
	public Customer deleteCustomer(Integer customerId, String key) throws CustomerException, LoginException {
		UserSession session = sdao.findByUuid(key);
		if (session == null) {
			throw new LoginException("You are not logged in");
		} else {
			if (session.getUserid() == customerId) {
				Optional<Customer> dele = cdao.findById(customerId);
				sdao.delete(session);
				cdao.delete(dele.get());
				return dele.get();
			} else {
				throw new CustomerException("Access Denied...");
			}
		}
	}

	@Override
	public List<Customer> viewAllCustomer(String key) throws CustomerException, LoginException {
		UserSession session = sdao.findByUuid(key);
		if (session == null) {
			throw new LoginException("You are not logged in");
		} else {
			if (session.getUserid() == 123456) {

				List<Customer> list = cdao.getCustomers();
				return list;
			} else {
				throw new CustomerException("Access Denied...");
			}
		}
	}

	@Override
	public Customer viewCustomer(Integer customerId, String key) throws CustomerException, LoginException {
		UserSession session = sdao.findByUuid(key);
		if (session == null) {
			throw new LoginException("You are not logged in");
		} else {
			if (session.getUserid() == customerId) {
				Optional<Customer> view = cdao.findById(customerId);
				return view.get();

			} else {
				throw new CustomerException("Access Denied...");
			}
		}

	}

}
