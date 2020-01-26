package com.bridgelabz.fundoo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.fundoo.model.Collaborator;
import com.bridgelabz.fundoo.model.Notes;

public interface CollaboratorRepository 
{

		@Query(value = "select * from collaborator where email=? and noteid=?",nativeQuery=true)
		Collaborator findOneByEmail(String email, long noteId);

		@Modifying
		@Query(value = "insert into collaborator(id,email,noteid)value(?,?,?)",nativeQuery=true)
		void addCollaborator(Notes notes,String email,Long noteid);
		
		@Query(value = "select * from collaborator where id=?",nativeQuery=true)
		Collaborator findById(Long id);

		@Modifying
		@Query(value = "delete from collaborator where collaborator_id=? and noteid=?",nativeQuery=true)
		void deleteCollaborator(long collaboratorId,long noteId);

		@Query(value = "select * from collaborator where noteid=?", nativeQuery = true)
		List<Collaborator> getAllNoteCollaborators(long noteId);

	
}
