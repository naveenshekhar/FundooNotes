package com.bridgelabz.fundoo.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.NotesService;

@RestController
public class NotesController {

	@Autowired
	NotesService service;
	
	@Autowired
	private ElasticsearchOperations operations;

	@PostMapping("/notes/create")
	public ResponseEntity<Responce> create(@Valid @RequestBody NotesDto noteDto, @RequestHeader String token) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new Responce("Notes created Successfully", 200));
	}

	@DeleteMapping("/deleteNotes/{token}")
	public ResponseEntity<Responce> deleteNotes(@RequestParam Long noteId, String token) {

		Long notes = service.delete(noteId, token);
		if (notes != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Responce("Notes Deleated Successfully", 200, notes));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("Failed..", 400));
		}
	}

	@PutMapping("/updateNotes/{id}/")
	public ResponseEntity<Responce> updateNotes(@PathVariable long id, @RequestBody NotesDto noteDto, String token) {

		Notes notes = service.update(id, noteDto, token);
		if (notes != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Responce("Notes updated Successfully", 200, notes));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("Failed to update", 400));
		}
	}

	@PutMapping("/pin/{noteId}/")
	public ResponseEntity<Responce> pin(@RequestHeader("token") String token, @PathVariable("noteId") Long noteId) {

		boolean result = service.pin(token, noteId);

		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Responce("Successfull...!!", 200, result));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("Failed to update...!!!", 400));
		}
	}

	@PutMapping("/isArchive/{token}")
	public ResponseEntity<Responce> isArchive(@RequestParam Long noteId, @PathVariable("token") String token) {
		System.out.println("Inside controller");
		boolean result = service.isArchive(noteId, token);

		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Responce("Successfull...!!", 200, result));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("Failed to update...!!!", 400));
		}
	}

	@PutMapping("/Trash/{noteId}/")
	public ResponseEntity<Responce> isTrash(@RequestHeader("token") String token, @PathVariable("noteId") Long noteId) {

		boolean result = service.trash(noteId, token);

		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Responce("Successfull...!!", 200, result));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("Failed to update...!!!", 400));
		}
	}
	
	@PostMapping("/notes/{searchById}")
	public ResponseEntity<Responce> getNotesById(@RequestParam("title") String title,
			 @RequestHeader("token") String token) {
	     List<Notes> notes=service.searchByTitle(title);
	     
	     return ResponseEntity.status(HttpStatus.FOUND).body(new Responce("Found",200 ));
		
	}

}
