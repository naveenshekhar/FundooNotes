package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;

public interface NotesService {

	Notes create(NotesDto noteDto, String token);

	Notes delete(int id);

	Notes update(long noteId, NotesDto notesDto, String token);

	boolean pin(); 
	
	boolean trash(int noteId, String token);
	
	boolean delete(int noteId,String token);

	

}
