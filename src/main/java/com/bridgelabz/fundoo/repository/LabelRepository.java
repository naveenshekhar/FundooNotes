package com.bridgelabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.model.Labels;
import com.bridgelabz.fundoo.model.User;

@Repository
public interface LabelRepository extends JpaRepository<Labels, Long>{

	@Query(value = "insert into Lables(labelName,LabelUser)"+"values(?,?)",nativeQuery = true) 
	void createLabel(String labelName,User LabelUser);
	

}
