package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.NotesService;

@RestController
public class NotesController {

	@Autowired
	NotesService service;

	@PostMapping("/createNotes")
	public ResponseEntity<Responce> createNotes(@RequestBody NotesDto notesdto) {

		Notes notes = service.create(notesdto);
		if (notes != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Responce("Notes created Successfully", 200, notesdto));
		} else {
			System.out.println("hello else");
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new Responce("Notes Already Exist", 400, notesdto));
		}
	}

}
