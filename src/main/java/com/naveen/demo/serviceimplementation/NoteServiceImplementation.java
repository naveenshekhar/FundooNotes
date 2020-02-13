package com.naveen.demo.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naveen.demo.model.Notes;
import com.naveen.demo.repository.NoteRepository;
import com.naveen.demo.service.NoteService;


@Service
public class NoteServiceImplementation implements NoteService {

	@Autowired
	NoteRepository noteRepository;
	
	Notes notes=new Notes();
	
	@Override
	public void create(String title, String content) {
		notes.setTitle(title);
		notes.setContent(content);
		String titleModel=notes.getTitle();
		String contentModel=notes.getContent();
		noteRepository.create(titleModel, contentModel);
		
	}

}
