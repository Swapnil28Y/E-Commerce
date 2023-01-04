package com.product.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exceptions.LoginException;
import com.product.exceptions.ProductException;
import com.product.model.Category;
import com.product.model.Product;
import com.product.model.UserSession;
import com.product.repository.CategoryDao;
import com.product.repository.ProductDao;
import com.product.repository.SessionDao;
@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductDao pDao;
	@Autowired
	private SessionDao sDao;
	@Autowired
	private CategoryDao cDao;
	@Override
	public Product registerProduct(Product product, String key) throws LoginException {
		UserSession user=sDao.findByUuid(key);
		if(user==null) {
			throw new LoginException("You are not logged in...");
		}else {
			if(user.getUserid()==123456) {
				Category ccc=product.getCategory();
						ccc.setProduct(product);
					Category ca=cDao.save(ccc);	
					product.setCategory(ca);
				Product p=pDao.save(product);
				return p;
			}else {
				throw new LoginException("You are restricted to do this task");
			}
		}
		
	}
//
//	@Override
//	public Product updateProduct(Product product, String key) throws LoginException, ProductException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Product deleteProduct(Integer productId, String key) throws LoginException, ProductException {
		UserSession user=sDao.findByUuid(key);
		if(user==null) {
			throw new LoginException("You are not logged in...");
		}else {
			if(user.getUserid()==123456) {
				Optional<Product> pro=pDao.findById(productId);
				if(pro.isPresent()) {
					Product p=pro.get();
					pDao.delete(p);
					return p;
				}else {
					throw new ProductException("Product is unavailable");
				}
				
			}else {
				throw new LoginException("You are restricted to do this task");
			}
		}
	}

	@Override
	public Product viewProduct(Integer productId) throws ProductException {
		Optional<Product> pro=pDao.findById(productId);
		if(pro.isPresent()) {
			Product pp=pro.get();
			return pp;
		}else {
			throw new ProductException("Product is unavailable");
		}
	}

	@Override
	public List<Product> viewAllProduct() throws ProductException {
		List<Product> list=pDao.findAll();
		if(list.isEmpty()) {
			throw new ProductException("Products are unavailable");
		}
		return list;
	}

}
