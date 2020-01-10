package com.bridgelabz.fundoo.repository;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.bridgelabz.fundoo.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	@Modifying
	@Transactional
	@Query(value="insert into user (first_name,last_name,phone_number,email,password) "
			+ "values (?,?,?,?,?)",nativeQuery=true)
	public void insertData(String first_name, String last_name, String phone_number,String email, String password);

	
	@Query(value = "select * from user where email=?", nativeQuery = true)
	User checkByEmail(String email);
	
}
