package com.bridgelabz.fundoo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.model.Labels;
import com.bridgelabz.fundoo.model.User;

@Repository
public interface LabelRepository extends JpaRepository<Labels, Long>{

	@Transactional
	@Modifying
	@Query(value = "insert into lables(label_name,user_id)"+"values(?,?)",nativeQuery = true) 
	void createLabel(String labelName,User LabelUser);

	@Transactional
	@Modifying
	@Query(value = "delete from lables where label_id=?",nativeQuery = true)
	void deleteLabel(Long labelId);

	@Transactional
	@Query(value = "select * from lables where user_id=?",nativeQuery = true)
	List<Labels> getAllLabel(long id);
	

}
