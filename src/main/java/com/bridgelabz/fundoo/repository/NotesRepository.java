package com.bridgelabz.fundoo.repository;

import java.sql.Date;
import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridgelabz.fundoo.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

	@Modifying
	@Transactional
	@Query(value = "insert into note (color,title, description,is_archived,is_pinned,creation_time,user_id)"
			+ "values (?,?,?,?,?,?,?)", nativeQuery = true)
	void insertData(String color,String title, String description,boolean is_archived,boolean is_pinned,Timestamp creation_time,long user_id);

	@Query(value = "select * from notes where note_id=?", nativeQuery = true)
	public Notes findbyId(long note_id);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM note WHERE id=?, nativeQuery = true",nativeQuery = true)
	void delete(int id);
	
	@Query(value = "update note set title=?,description=? where id=?", nativeQuery = true)
	void update(String title, String description, long id);

}
