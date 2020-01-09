package com.bridgelabz.fundoo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.fundoo.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value = "select * from user where email=?", nativeQuery = true)
	User checkByEmail(String email);
	
	@Modifying
	@Transactional
	@Query(value="insert into FundooNotes.user (firstName,lastName,phoneNumber,email,password) "
			+ "values (?,?,?,?,?)",nativeQuery=true)
	public void insertData(String firstName, String lastName, long phoneNumber,String email, String password);

}
