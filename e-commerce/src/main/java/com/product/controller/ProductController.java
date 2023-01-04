package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.Service.ProductService;
import com.product.exceptions.LoginException;
import com.product.exceptions.ProductException;
import com.product.model.Product;

@RestController
public class ProductController {
	@Autowired
	private ProductService pservice;

	@PostMapping("/products")
	public ResponseEntity<Product> registerProduct(@RequestBody Product product, @RequestParam String key)
			throws LoginException {

		Product pro = pservice.registerProduct(product, key);
		return new ResponseEntity<Product>(pro, HttpStatus.CREATED);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer productId, @RequestParam String key)
			throws LoginException, ProductException {

		Product product = pservice.deleteProduct(productId, key);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer productId ) throws ProductException{
		Product pro=pservice.viewProduct(productId);
		return new ResponseEntity<Product>(pro, HttpStatus.OK);
	}
	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getAllProduct() throws ProductException{
		List<Product> pro=pservice.viewAllProduct();
		return new ResponseEntity<List<Product>>(pro, HttpStatus.OK);
	}
}
