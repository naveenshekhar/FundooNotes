package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;

public interface NotesService {

	Notes create(NotesDto noteDto, String token);


	Notes update(long noteId, NotesDto notesDto, String token);

	boolean pin(String token, Long noteId);

	boolean trash(long noteId, String token);

	Long delete(Long noteId, String token);

	boolean isArchive(Long noteId, String token);

	

}
