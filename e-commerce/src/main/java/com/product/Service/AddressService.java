package com.product.Service;

import java.util.List;

import com.product.exceptions.AddressException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.model.Address;

public interface AddressService {

	public Address registerAddress(Address address, String key) throws CustomerException, LoginException;

	public Address updateAddress(Address address, String key)
			throws CustomerException, AddressException, LoginException;

	public Address deleteAddress(Integer addressId, String key)
			throws CustomerException, AddressException, LoginException;

	public Address viewAddress(Integer addressId, String key)
			throws CustomerException, AddressException, LoginException;

	public List<Address> viewAllAddress(String key) throws AddressException, LoginException;
}
