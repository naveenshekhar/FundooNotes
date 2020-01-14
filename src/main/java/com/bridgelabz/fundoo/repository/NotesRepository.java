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
	@Query(value = "insert into Note (title,description,userId,reminder,color)"
			+ "values (?,?,?,?,?)", nativeQuery = true)
	void insertData(String title, String description, int userId, Date reminder, String color);
}
