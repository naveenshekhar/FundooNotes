package com.bridgelabz.fundoo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CollaboratorRepository 
{
	@Transactional
	@Modifying
	@Query(value = "",nativeQuery = true)
	void addCollaborator();
	
	@Transactional
	@Modifying
	@Query(value = "",nativeQuery = true)
	void removeCollaborator();
	
	

}
