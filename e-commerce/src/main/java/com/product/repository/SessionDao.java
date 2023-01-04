package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.model.UserSession;
@Repository
public interface SessionDao extends JpaRepository<UserSession, Integer> {
	public UserSession findByUuid(String uuid);
	
}
