package com.bridgelabz.fundoo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

//	@Autowired
//	private ElasticsearchOperations operations;

	@SuppressWarnings("deprecation")
	@PostMapping("/notes/create")
	public ResponseEntity<Responce> create(@Valid @RequestBody NotesDto noteDto, @RequestHeader String token) {
		Notes note = service.create(noteDto, token);
		if (note != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Responce("Notes created Successfully", 200));
		}
		return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(new Responce("Notes created Successfully", 400));
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

	@PostMapping("/notes/{searchNotes}")
	public ResponseEntity<Responce> getNotesByName(@RequestParam("title") String title,
			@RequestHeader("token") String token) {
		Notes notes = service.searchByTitle(title);
		System.out.println(notes);
		if (notes != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(new Responce("Found", 200, notes));
		} else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Responce("Not Found", 400));
	}

	@PostMapping("/notes/getAllNotes")
	public ResponseEntity<Responce> getAllNotes(String token) {
		List<Notes> notes = service.getAllNotes(token);

		if (notes != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(new Responce("Found", 200, notes));
		} else

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Responce("Not Found", 400));
	}

	@PostMapping("/notes/elasticSearch")
	public ResponseEntity<Responce> noteElasticSearch(@RequestParam String word) {

		Map<String, Object> note = service.elasticSearch(word);

		if (note != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(new Responce("Found", 200, note));
		} else

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Responce("Not Found", 400));
	}
}
