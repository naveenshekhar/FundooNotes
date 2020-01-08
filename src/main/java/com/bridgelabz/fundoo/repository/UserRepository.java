package com.bridgelabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.fundoo.model.User;


public interface UserRepository extends JpaRepository<com.bridgelabz.fundoo.model.User, Long>{
	
	@Query(value = "select * from user where email=?", nativeQuery = true)
	User checkByEmail(String email);
	@Modifying
	@Query("insert into FundooNotes.user (firstName,lastName,phoneNumber,email,isPhoneVerified,isEmailVerified,password) values (?,?,?,?,?,?,?)")
	public void insertData(String firstName, String lastName, int phoneNumber, boolean isPhoneVerified,
			boolean isEmailVerified, String password);

}
