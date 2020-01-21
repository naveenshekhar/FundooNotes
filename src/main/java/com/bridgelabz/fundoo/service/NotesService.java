package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;

public interface NotesService {
	// Notes create(NotesDto noteDto, String token);

	Notes create(NotesDto noteDto, String token);

	Notes delete(NotesDto noteDto);

	Notes update(NotesDto noteDto);

}
