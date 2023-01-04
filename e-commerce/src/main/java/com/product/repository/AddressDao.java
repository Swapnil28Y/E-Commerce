package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Address;

public interface AddressDao extends JpaRepository<Address, Integer> {

}
