package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.Service.CustomerService;
import com.product.exceptions.AddressException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService cservice;

	@PostMapping("/customersRegistration")
	public ResponseEntity<Customer> registerNewCustomer(@RequestBody Customer customer)
			throws CustomerException, AddressException {
		Customer user = cservice.registerCustomer(customer);

		return new ResponseEntity<Customer>(user, HttpStatus.CREATED);
	}

	@PutMapping("/customersUpdate")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @RequestParam String key)
			throws CustomerException, LoginException {
		Customer user = cservice.updateCustomer(customer, key);

		return new ResponseEntity<Customer>(user, HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Integer customerId, @RequestParam String key)
			throws CustomerException, LoginException{
		Customer user = cservice.deleteCustomer(customerId, key);

		return new ResponseEntity<Customer>(user, HttpStatus.ACCEPTED);
	}
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomer(@RequestParam String key) throws CustomerException, LoginException{
		List<Customer> user = cservice.viewAllCustomer(key);

		return new ResponseEntity<List<Customer>>(user, HttpStatus.ACCEPTED);
	}
	@GetMapping("/getCustomers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Integer customerId, @RequestParam String key)
			throws CustomerException, LoginException{
		Customer user = cservice.viewCustomer(customerId, key);

		return new ResponseEntity<Customer>(user, HttpStatus.ACCEPTED);
	}

}
