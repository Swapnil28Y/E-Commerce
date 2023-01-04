package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.Service.CartService;
import com.product.exceptions.CartException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.exceptions.ProductException;
import com.product.model.Cart;
import com.product.model.Product;

@RestController
public class CartController {
	@Autowired
	private CartService cservice;

	@PostMapping("/carts")
	public ResponseEntity<Cart> addToCart(@RequestParam Integer productId, @RequestParam Integer quantity,
			@RequestParam String key) throws CartException, LoginException, ProductException, CustomerException {

		Cart cartItem = cservice.addProductToCart(productId, quantity, key);

		return new ResponseEntity<Cart>(cartItem, HttpStatus.ACCEPTED);
	}

	@GetMapping("/carts")
	public ResponseEntity<List<Product>> getAllProductInCart(@RequestParam String key)
			throws CartException, LoginException {

		List<Product> list = cservice.viewAllProducts(key);

		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	@DeleteMapping("/carts")
	public ResponseEntity<List<Product>> removeAProductFromCart(@RequestParam Integer productId,
			@RequestParam String key) throws CartException, ProductException, LoginException {

		List<Product> list = cservice.removeProductFromCart(productId, key);

		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);

	}

	@PutMapping("/carts")
	public ResponseEntity<List<Product>> updateProductQuantity(@RequestParam Integer productId,
			@RequestParam Integer quantity, @RequestParam String key)
			throws CartException, LoginException, ProductException {

		List<Product> list = cservice.updateProductQuantity(productId, quantity, key);

		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	@DeleteMapping("/cart")
	public ResponseEntity<Cart> removeAllProducts(@RequestParam String key) throws CartException, LoginException {
		Cart cart = cservice.removeAllProducts(key);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

}
