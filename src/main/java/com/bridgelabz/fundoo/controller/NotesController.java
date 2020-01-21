package com.bridgelabz.fundoo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.NotesService;

@RestController
public class NotesController {

	@Autowired
	NotesService service;

	@PostMapping("/notes/create")
	public ResponseEntity<Responce> create(@Valid @RequestBody NotesDto noteDto, @RequestHeader String token) {
		Notes notes = service.create(noteDto, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Responce("Notes created Successfully", 200, notes));
	}

	@PostMapping("/deleteNotes")
	public ResponseEntity<Responce> deleteNotes(@RequestBody NotesDto notesdto) {

		Notes notes = service.delete(notesdto);
		if (notes != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Responce("Notes Deleated Successfully", 200, notes));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("Failed..", 400, notesdto));
		}
	}

	@PostMapping("/updateNotes")
	public ResponseEntity<Responce> updateNotes(@RequestBody NotesDto notesdto) {

		Notes notes = service.update(notesdto);
		if (notes != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Responce("Notes updated Successfully", 200, notes));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new Responce("Failed to update", 400, notesdto));
		}
	}
}
