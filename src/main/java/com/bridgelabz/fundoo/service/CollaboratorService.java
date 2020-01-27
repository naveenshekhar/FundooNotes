package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.dto.CollaboratorDto;
import com.bridgelabz.fundoo.model.Collaborator;

@Component
public interface CollaboratorService {
	public Collaborator addCollaborator(CollaboratorDto collaboratorDto, String token, Long noteId);

	public Optional<Collaborator> deleteCollaborator(Long collaboratorId, String token, Long noteId);

	public List<Collaborator> getAllCollaborators(String token, Long noteId);
}