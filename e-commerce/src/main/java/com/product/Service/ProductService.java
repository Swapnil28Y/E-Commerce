package com.product.Service;

import java.util.List;

import com.product.exceptions.LoginException;
import com.product.exceptions.ProductException;
import com.product.model.Product;

public interface ProductService {

public Product registerProduct(Product product,String key) throws LoginException;
	
//	public Product updateProduct(Product product,String key) throws LoginException,ProductException;
	
	public Product deleteProduct(Integer productId,String key) throws LoginException,ProductException;
	
	public Product viewProduct(Integer productId) throws ProductException;
	
	public List<Product> viewAllProduct() throws ProductException;
	
}
