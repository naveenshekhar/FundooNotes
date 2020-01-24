package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.model.Collaborator;

public interface CollaboratorService
{

	Collaborator addCollaborator(String token, long noteId);
	
	Collaborator removeCollaborator(String token, long noteId);
}
