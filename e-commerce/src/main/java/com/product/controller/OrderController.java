package com.product.controller;

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

import com.product.Service.OrderService;
import com.product.exceptions.CartException;
import com.product.exceptions.LoginException;
import com.product.exceptions.OrderException;
import com.product.exceptions.ProductException;
import com.product.model.Orders;

@RestController
public class OrderController {
	@Autowired
	private OrderService oservice;

	@PostMapping("/orders")
	public ResponseEntity<Orders> registerOrder(@RequestBody Orders order, @RequestParam String key)
			throws OrderException, CartException, LoginException, ProductException {

		Orders orders = oservice.registerOrder(order, key);

		return new ResponseEntity<Orders>(orders, HttpStatus.CREATED);
	}

	@PutMapping("/orders")
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders order, @RequestParam String key)
			throws OrderException, LoginException {

		Orders orders = oservice.updateOrder(order, key);

		return new ResponseEntity<Orders>(orders, HttpStatus.ACCEPTED);

	}
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<Orders> removeOrder(@PathVariable("orderId") Integer orderId,@RequestParam String key) throws OrderException, LoginException{
		Orders orders=oservice.removeOrder(orderId, key);
		
		return new ResponseEntity<Orders>(orders, HttpStatus.OK);
	}
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Orders> viewOrder(@PathVariable("orderId") Integer orderId,@RequestParam String key) throws OrderException, LoginException{
		
		Orders orders=oservice.viewOrder(orderId ,key);
		
		return new ResponseEntity<Orders>(orders, HttpStatus.OK);

	}

}
