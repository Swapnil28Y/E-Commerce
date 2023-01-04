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

import com.product.Service.AddressService;
import com.product.exceptions.AddressException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.model.Address;

@RestController
public class AddressController {

	@Autowired
	private AddressService aservice;

	@PostMapping("/addresses")
	public ResponseEntity<Address> registerAddress(@RequestBody Address address, @RequestParam String key)
			throws CustomerException, LoginException {
		Address ad = aservice.registerAddress(address, key);
		return new ResponseEntity<Address>(ad, HttpStatus.CREATED);
	}

	@PutMapping("/addresses")
	public ResponseEntity<Address> updateAddresses(@RequestBody Address address, @RequestParam String key)
			throws CustomerException, AddressException, LoginException {
		Address ad = aservice.updateAddress(address, key);
		return new ResponseEntity<Address>(ad, HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/addresses")
	public ResponseEntity<Address> deleteAddresses (@RequestParam Integer addressId, @RequestParam String key)
			throws CustomerException, AddressException, LoginException  {
		Address ad = aservice.deleteAddress(addressId, key);
		return new ResponseEntity<Address>(ad, HttpStatus.OK);
	}
	@GetMapping("/addresses/{id}")
	public ResponseEntity<Address> viewAddresses (@PathVariable("id") Integer addressId, @RequestParam String key)
			throws CustomerException, AddressException, LoginException  {
		Address ad = aservice.viewAddress(addressId, key);
		return new ResponseEntity<Address>(ad, HttpStatus.OK);
	}
	@GetMapping("/allAddresses")
	public ResponseEntity<List<Address>> viewAddresses (@RequestParam String key)
			throws CustomerException, AddressException, LoginException  {
		List<Address> ad = aservice.viewAllAddress(key);
		return new ResponseEntity<List<Address>>(ad, HttpStatus.OK);
	}
}



