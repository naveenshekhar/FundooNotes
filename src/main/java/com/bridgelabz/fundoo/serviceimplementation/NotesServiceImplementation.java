package com.bridgelabz.fundoo.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.repository.NotesRepository;
import com.bridgelabz.fundoo.service.NotesService;

@Service
public class NotesServiceImplementation implements NotesService {

	Notes notes;
	@Autowired
	private NotesRepository notesrepo;

	@Override
	public Notes create(NotesDto noteDto) {

		notes.setTitle(noteDto.getTitle());
		notes.setDescription(noteDto.getDescription());
		notesrepo.insertData(notes.getTitle(), notes.getDescription(), notes.getUserId(), notes.getReminder(),
				notes.getColor());
		return notes;
	}

}
