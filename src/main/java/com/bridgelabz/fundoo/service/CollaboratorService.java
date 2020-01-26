package com.bridgelabz.fundoo.service;

import java.util.List;

import com.bridgelabz.fundoo.dto.CollaboratorDto;
import com.bridgelabz.fundoo.model.Collaborator;

public interface CollaboratorService {
	public Collaborator addCollaborator(CollaboratorDto collaboratorDto, String token, long noteId);

	public Collaborator deleteCollaborator(long collaboratorId, String token, long noteId);

	public List<Collaborator> getAllCollaborators(String token, long noteId);
}