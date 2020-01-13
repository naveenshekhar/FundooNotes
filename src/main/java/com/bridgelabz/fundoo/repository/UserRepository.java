package com.bridgelabz.fundoo.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridgelabz.fundoo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Registration
	@Modifying
	@Transactional
	@Query(value = "insert into user (first_name,last_name,phone_number,email,password)"
			+ "values (?,?,?,?,?)", nativeQuery = true)
	void insertData(String first_name, String last_name, String phone_number, String email, String password);

	// Login
	@Query(value = "select * from user where email=?", nativeQuery = true)
	User checkByEmail(String email);

	// Forget password
	@Modifying
	@Transactional
	@Query(value = "UPDATE user set password = ? where email = ?", nativeQuery = true)
	void changepassword(String password, String email);
	
	//Finding email_ID
	@Query(value = "SELECT first_name,last_name,phone_number,email,password from user where email=?",nativeQuery = true)
	User FindByEmail(String email);
}
