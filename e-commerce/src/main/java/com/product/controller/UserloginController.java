package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.DTO.AdminDTO;
import com.product.DTO.LoginDTO;
import com.product.Service.UserService;
import com.product.exceptions.AdminDTOException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.model.UserSession;

@RestController
public class UserloginController {

	@Autowired
	private UserService uservice;
	@PostMapping("/login")
	public ResponseEntity<UserSession> userLogin(@RequestBody LoginDTO logindto) throws CustomerException, LoginException{
		
		UserSession users= uservice.loginForUserCustomer(logindto);
		
		return new ResponseEntity<UserSession>(users,HttpStatus.CREATED);
		
	}
	@GetMapping("/logout")
public ResponseEntity<String> userLogout(@RequestParam String key) throws LoginException{
		
		String users= uservice.logoutForUserCustomer(key);
		
		return new ResponseEntity<String>(users,HttpStatus.OK);
		
	}
	@PostMapping("/Adminlogin")
	public ResponseEntity<UserSession> adminLogin( @RequestBody AdminDTO admindto) throws AdminDTOException{
			
			UserSession users= uservice.loginForUserAdmin(admindto);
			
			return new ResponseEntity<UserSession>(users,HttpStatus.CREATED);
			
		}
}
