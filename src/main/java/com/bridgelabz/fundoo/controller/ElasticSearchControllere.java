package com.bridgelabz.fundoo.controller;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.repository.NotesRepository;

@RestController
public class ElasticSearchControllere {
	private ElasticsearchOperations operations;

	private NotesRepository notesrepo;
	
	@PostMapping("/notes/{search}")
	public Notes findNotes(@PathVariable("id") String id) {

		Notes notes = operations.queryForObject(GetQuery.getById(id), Notes.class);

//	 notes=	notesrepo.findAllById(id);
		return notes;

		
 	}

}
