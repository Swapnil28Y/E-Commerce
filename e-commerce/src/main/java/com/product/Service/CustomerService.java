package com.product.Service;

import java.util.List;

import com.product.exceptions.AddressException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.model.Customer;

public interface CustomerService {

	public Customer registerCustomer(Customer customer) throws CustomerException,AddressException;
	public Customer updateCustomer(Customer customer,String key)throws CustomerException,LoginException;
	public Customer deleteCustomer(Integer customerId,String key) throws CustomerException,LoginException;
	public List<Customer> viewAllCustomer(String key) throws CustomerException,LoginException;
	public Customer viewCustomer(Integer customerId,String key) throws CustomerException,LoginException;
}
