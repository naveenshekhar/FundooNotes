package com.naveen.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.demo.dto.NoteDto;
import com.naveen.demo.service.NoteService;

@RestController
public class NoteController {
	
	NoteDto noteDto=new NoteDto();
	
	@Autowired
	NoteService noteService;

	@PostMapping("/note/create")
	public void createNotes(@RequestBody NoteDto noteDto) {

		String title = noteDto.getTitle();
		String content = noteDto.getContent();
		
		noteService.create(title,content);

	}

}
