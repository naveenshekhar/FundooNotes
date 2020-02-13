package com.naveen.demo.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.naveen.demo.model.Notes;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {

	@Transactional
	@Modifying
	@Query(value = "insert into notes(title, content) values(?,?)", nativeQuery = true)
	void create(String title, String content);

}
