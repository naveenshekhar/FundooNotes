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
	
	
//	@Repository
//	@Transactional
//	public interface CollaboratorRepository extends JpaRepository<Collaborator,Object>{
//
//		@Query(value = "select * from collaborator where email=? and noteid=?",nativeQuery=true)
//		Collaborator findOneByEmail(String email, long noteId);
//
//		@Modifying
//		@Query(value = "insert into collaborator(id,email,noteid)value(?,?,?)",nativeQuery=true)
//		void addCollaborator(Long id,String email,Long noteid);
//		
//		@Query(value = "select * from collaborator where id=?",nativeQuery=true)
//		Collaborator findById(Long id);
//
//		@Modifying
//		@Query(value = "delete from collaborator where id=? and noteid=?",nativeQuery=true)
//		void deleteCollaborator(long collaboratorId,long noteId);
//
//		@Query(value = "select * from collaborator where noteid=?", nativeQuery = true)
//		List<Collaborator> getAllNoteCollaborators(long noteId);
//
//	}
}
