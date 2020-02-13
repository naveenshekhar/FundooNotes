package com.naveen.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.naveen.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Modifying
	@Transactional
	@Query(value = "insert into user(first_name,last_name,email,password) values(?,?,?,?)", nativeQuery = true)
	void registerUser(String first_name, String last_name, String email, String password);

}
