package com.bridgelabz.fundoo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.NotesService;
import com.bridgelabz.fundoo.serviceimplementation.NotesServiceImplementation;

@RestController
public class NotesController {

	@Autowired
	NotesService service;

	@PostMapping("/notes/create")
	public ResponseEntity<Responce> create(@Valid @RequestBody NotesDto noteDto, @RequestHeader String token) {
		Notes notes = service.create(noteDto, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Responce("Notes created Successfully", 200));
	}

	@PostMapping("/deleteNotes")
	public ResponseEntity<Responce> deleteNotes(@RequestBody int id) {

		Notes notes = service.delete(id);
		if (notes != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Responce("Notes Deleated Successfully", 200, notes));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("Failed..", 400));
		}
	}

	@PostMapping("/updateNotes/{id}/")
	public ResponseEntity<Responce> updateNotes(@PathVariable long id, @RequestBody NotesDto noteDto, String token) {

		Notes notes = service.update(id, noteDto, token);
		if (notes != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Responce("Notes updated Successfully", 200, notes));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("Failed to update", 400));
		}
	}

	@PostMapping("/pinnedNote/{noteId}/")
	public ResponseEntity<Responce> pinnedNote(@RequestHeader("token") String token,
			@PathVariable("noteId") Long noteId) {

		boolean result = service.pin();

		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Responce("Notes is pinned", 200, result));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("Not pinned", 400));
		}
	}
}
