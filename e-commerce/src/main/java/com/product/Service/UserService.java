package com.product.Service;

import com.product.DTO.AdminDTO;
import com.product.DTO.LoginDTO;
import com.product.exceptions.AdminDTOException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.model.UserSession;

public interface UserService {

	public UserSession loginForUserCustomer(LoginDTO logindto)throws CustomerException,LoginException;
	public String logoutForUserCustomer(String key)throws LoginException;
	public UserSession loginForUserAdmin(AdminDTO admindto)throws AdminDTOException;
}
