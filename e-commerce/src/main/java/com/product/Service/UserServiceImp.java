package com.product.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.DTO.AdminDTO;
import com.product.DTO.LoginDTO;
import com.product.exceptions.AdminDTOException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.model.Customer;
import com.product.model.UserSession;
import com.product.repository.CustomerDao;
import com.product.repository.SessionDao;

import net.bytebuddy.utility.RandomString;
@Service
public class UserServiceImp implements UserService{

	@Autowired
	private SessionDao sdao;
	@Autowired
	private CustomerDao cdao;
	@Override
	public UserSession loginForUserCustomer(LoginDTO logindto) throws CustomerException, LoginException{
		Customer cust= cdao.findByMobilenumber(logindto.getMobilenumber());
		if(cust!=null) {
			Optional<UserSession> usession= sdao.findById(cust.getCustomerid());
			if(usession.isPresent()) {
				throw new LoginException("Already logged in");
			}else {
				if(logindto.getPassword().equals(cust.getPassword())) {
					String key=RandomString.make(6);
					UserSession use=new UserSession(cust.getCustomerid(),key,LocalDateTime.now());
					UserSession us= sdao.save(use);
					return us;
					
					}else {
						throw new LoginException("Wrong Credentials");
					}
			}
			
		}else {
			throw new CustomerException("Customer data not found");
		}
	}

	@Override
	public String logoutForUserCustomer(String key) throws LoginException {
		UserSession use= sdao.findByUuid(key);	
		if(use!=null) {
			 sdao.delete(use);
			 return "Logout";
		}
		throw new LoginException("given key is wrong");
	}

	@Override
	public UserSession loginForUserAdmin(AdminDTO admindto) throws AdminDTOException {
		if(admindto.getMobilenumber().equals(1234567890)) {
			Optional<Customer> admin = cdao.findById(admindto.getId());
			if(admin.isPresent()) {
				throw new AdminDTOException("Already login");
			}else {
				if(admindto.getPassword().equals("1224354461")) {
					if(admindto.getId()==123456) {
						String key=RandomString.make(8);
						UserSession usee=new UserSession(admindto.getId(),key,LocalDateTime.now());
						UserSession ans= sdao.save(usee);
						return ans;
					}else {
						throw new AdminDTOException("Id is wrong");
					}
				}else {
					throw new AdminDTOException("Password is wrong");
				}
			}
			
		}else {
			throw new AdminDTOException("Details are not found");
		}
	}

}
