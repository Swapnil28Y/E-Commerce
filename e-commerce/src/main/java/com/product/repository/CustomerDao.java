package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.model.Customer;
@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer>{
	public Customer findByMobilenumber(String mobilenumber);
	
	@Query("select c from customers c")
	public List<Customer> getCustomers();
}
