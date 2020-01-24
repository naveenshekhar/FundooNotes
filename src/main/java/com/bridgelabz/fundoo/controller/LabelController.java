package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.DeleteLabelDto;
import com.bridgelabz.fundoo.dto.LabelDto;
import com.bridgelabz.fundoo.model.Labels;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.LabelService;

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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce("Failed To create.", 400));
		}
	}

	@DeleteMapping("/deleteLabels/")
	public ResponseEntity<Responce> deleteLabels(@RequestBody DeleteLabelDto label, String token) {
		Labels labels = service.delete(label, token);
		if (labels != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Responce("Deleted Successfully", 200));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce("Failed To Delete..!!", 400));
		}
	}
	
	@PostMapping("/GetAllLabels/")
	public ResponseEntity<Responce> getAllLabels(String token) {

		Labels labels = service.getAllLabels(token);
		if (labels != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Responce("All Lables..", 200));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Responce("Failed to retrive data..!!", 400));
		}
	}

	@PostMapping(name = "/mapLableToNote")
	public ResponseEntity<Responce> mapLableToNote()
	{
		
		return null;
		
	}

}
