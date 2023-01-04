package com.product.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AddressServiceImp implements AddressService {

	@Autowired
	private AddressDao adao;
	@Autowired
	private CustomerDao cdao;

	@Autowired
	private SessionDao sdao;

	@Override
	public Address registerAddress(Address address, String key) throws CustomerException, LoginException {
		UserSession uses= sdao.findByUuid(key);
		if(uses==null){
			throw new LoginException("You havent logged in");
		}else {
			Optional<Customer> se= cdao.findById(uses.getUserid());
			if(se.isPresent()) {
				address.setCustomer(se.get());
				Address aa= adao.save(address);
				se.get().getAddress().add(address);
				Customer ccc= cdao.save(se.get());
				return aa;
			}else {
				throw new CustomerException("Customer not found with given data");
			}
			
		}
	}

	@Override
	public Address updateAddress(Address address, String key)
			throws CustomerException, AddressException, LoginException {
		UserSession uses= sdao.findByUuid(key);
		if(uses==null){
			throw new LoginException("You havent logged in");
		}else {
			Optional<Customer> se= cdao.findById(uses.getUserid());
			if(se.isPresent()) {
				List<Address> addd=se.get().getAddress();
				if(addd.contains(address)) {
					address.setCustomer(se.get());
					Address a= adao.save(address);
					Customer ccc= cdao.save(se.get());
					return a;
					
				}else {
					throw new AddressException("Address not found");
				}
			}else {
				throw new CustomerException("Data not found");
			}

		}
	}

	@Override
	public Address deleteAddress(Integer addressId, String key)
			throws CustomerException, AddressException, LoginException {
		UserSession uses= sdao.findByUuid(key);
		if(uses==null){
			throw new LoginException("You havent logged in");
		}else {
			Optional<Address> aa= adao.findById(addressId);
			if(aa.isPresent()) {
				Address fin= aa.get();
				Optional<Customer> cust= cdao.findById(uses.getUserid());
				List<Address> list= cust.get().getAddress();
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getAddressid()==addressId) {
						list.remove(i);
					}
				}
				cust.get().setAddress(list);
				cdao.save(cust.get());
				adao.delete(fin);
				return fin;
				
			}else {
				throw new AddressException("Address not found...");
			}
			
		}
	}

	@Override
	public Address viewAddress(Integer addressId, String key)
			throws CustomerException, AddressException, LoginException {
		UserSession uses= sdao.findByUuid(key);
		if(uses==null){
			throw new LoginException("You havent logged in");
		}else {
			Optional<Address> add= adao.findById(addressId);
			if(add.isEmpty()) {
				throw new AddressException("Address not found with given id");
			}else {
				Address a= add.get();
				return a;
			}
		}
	}

	@Override
	public List<Address> viewAllAddress(String key) throws AddressException, LoginException {
		UserSession uses= sdao.findByUuid(key);
		if(uses==null){
			throw new LoginException("You havent logged in");
		}else {
		 	List<Address> list= adao.findAll();
		 	if(list.size()==0) {
		 		throw new AddressException("There are no addresses");
		 	}else {
		 		return list;
		 	}
		}
	}

}
