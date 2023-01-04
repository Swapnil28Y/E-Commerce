package com.product.Service;

import java.util.List;

import com.product.exceptions.CartException;
import com.product.exceptions.CustomerException;
import com.product.exceptions.LoginException;
import com.product.exceptions.ProductException;
import com.product.model.Cart;
import com.product.model.Product;

public interface CartService {
public Cart addProductToCart(Integer productId, int quantity,String key) throws CartException, LoginException, ProductException,CustomerException ;
	
	public List<Product> removeProductFromCart(Integer productId,String key) throws CartException, ProductException, LoginException  ;
	
	public List<Product> updateProductQuantity(Integer productId,Integer quantity,String key) throws CartException, LoginException, ProductException ;
	
	public Cart removeAllProducts(String key) throws CartException, LoginException ;
	
	public List<Product> viewAllProducts(String key)  throws CartException, LoginException;
}
