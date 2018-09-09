package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
}
