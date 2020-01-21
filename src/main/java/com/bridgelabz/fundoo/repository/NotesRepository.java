package com.bridgelabz.fundoo.repository;

import java.sql.Date;

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
	@Query(value = "insert into Note (title,description,user_id,reminder,color)"
			+ "values (?,?,?,?,?)", nativeQuery = true)
	void insertData(String title, String description, int userId, Date reminder, String color);

	@Query(value = "select * from notes where note_id=?", nativeQuery = true)
	public Notes findById(long note_id);
	
	@Modifying
	@Transactional
	@Query(value = "delete from note where title=?", nativeQuery = true)
	void delete(String title);

	@Query(value = "update note set title=?,description=? where id=?", nativeQuery = true)
	void update(String title, String description, long id);

}
