package com.bridgelabz.fundoo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.LabelDto;
import com.bridgelabz.fundoo.model.Labels;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.LabelService;
import com.bridgelabz.fundoo.utility.JwtGenerator;

@RestController
public class LabelController {

	@Autowired
	private LabelService service;

	@PostMapping("/createLabels/")
	public ResponseEntity<Responce> crateLabels(@RequestBody LabelDto label, String token) {
		Labels labels = service.create(label, token);
		if (labels != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Responce("Registered Successfully", 200));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("User Already Exist", 400));
		}
	}

//	@DeleteMapping("/deleteLabels/")
//	public ResponseEntity<Responce> deleteLabels() {
//		return null;
//	}
//	
//	
//	@PutMapping("/updateNotes/")
//	public ResponseEntity<Responce> updateNotes()
//	{
//		return null;
//	}

}
