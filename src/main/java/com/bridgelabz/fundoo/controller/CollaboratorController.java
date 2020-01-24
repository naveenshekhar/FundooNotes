package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.CollaboratorService;
import com.bridgelabz.fundoo.utility.JwtGenerator;

@RestController
public class CollaboratorController {
	@Autowired
	JwtGenerator tokenGenerator;

	@Autowired
	Notes notes;
	
	@Autowired
	CollaboratorService service;

	@PostMapping("/addCollaborator")
	public ResponseEntity<Responce> addCollaborator(String token, NotesDto note) {
		//long noteId=notes.getNoteId();
			
			
					
		
		
		return null;

	}

}
