package com.bridgelabz.fundoo.repository;

import java.awt.print.Pageable;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.dto.ForgetPaswordDto;
import com.bridgelabz.fundoo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

	@Query(value = "insert into user (first_name,last_name,phone_number,email,password) values (?,?,?,?,?)", nativeQuery = true)
	void insertData(String firstName, String lastName, String phoneNumber, String email, String password);

	// Login
	@Query(value = "select * from user where email=?", nativeQuery = true)
	User checkByEmail(ForgetPaswordDto email);

	// Forget password
	@Modifying
	@Transactional
	@Query(value = "UPDATE user set password = ? where id = ?", nativeQuery = true)
	void changepassword(String password, long id);

	@Query(value = "SELECT * from user where email=?", nativeQuery = true)
	User FindByEmail(String email);

	@Query(value = "SELECT * FROM user WHERE id=?", nativeQuery = true)
	User findbyId(Long id);

	@Modifying
	@Transactional
	@Query(value = "update user set is_verified =  true where id=?", nativeQuery = true)
	void updateIsVarified(Long id);

	
	//List<User> findAllUsers(User user, Pageable pageable);
}
