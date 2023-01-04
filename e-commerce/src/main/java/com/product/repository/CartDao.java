package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.model.Cart;
import com.product.model.Customer;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
	public Cart findByCustomer(Customer customer);
}
