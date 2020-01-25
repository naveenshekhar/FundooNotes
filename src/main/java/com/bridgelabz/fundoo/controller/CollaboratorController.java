package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.CollaboratorService;
import com.bridgelabz.fundoo.utility.JwtGenerator;

@RestController
public class CollaboratorController {
	@Autowired
	JwtGenerator tokenGenerator;

	@Autowired
	Notes notes;

	@Autowired
	CollaboratorService service;

	@PostMapping("/addCollaborator")
	public ResponseEntity<Responce> addCollaborator(String token, NotesDto note) {
		// long noteId=notes.getNoteId();
		return null;
		
//		
//		@RestController
//		@RequestMapping("/collaborator")
//		public class CollaboratorController {
//
//			@Autowired
//			private CollaboratorServiceInf collaboratorServiceInf;
//
//			@PostMapping("/addCollaborator/{noteId}")
//			private ResponseEntity<Responses> addCollaborator(@RequestBody CollaboratorDto collaboratorDto,
//					@PathVariable("noteId") long noteId, @RequestHeader("token") String token) {
//
//				Collaborator result = collaboratorServiceInf.addCollaborator(collaboratorDto, token, noteId);
//
//				return result != null
//						? ResponseEntity.status(HttpStatus.OK).body(new Responses("add collabrator sucessfully!!!", 200))
//						: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Responses("Something went wrong!!!", 400));
//			}
//
//			@DeleteMapping("/deleteCollaborator/{noteId}")
//			@ApiOperation(value = "Api to remove collaborator", response = Responses.class)
//			public ResponseEntity<Responses> deleteCollaborator(@PathVariable(value = "noteId") Long noteId,
//					@RequestHeader("token") String token, @RequestHeader("collaboratorId") Long collaboratorId) {
//				
//				Collaborator result = collaboratorServiceInf.deleteCollaborator(collaboratorId, token, noteId);
//				return result != null
//						? ResponseEntity.status(HttpStatus.OK).body(new Responses("delete collabrator sucessfully!!!", 200))
//						: ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Responses("Something went wrong!!!", 400));
//			}
//			
//			@GetMapping("/getAllNoteCollaborat/{noteId}")
//			@ApiOperation(value = "Api to show a collaborator", response = Responses.class)
//			public ResponseEntity<Responses> getAllCollaborator(@PathVariable(value = "noteId") Long noteId,
//					@RequestHeader("token") String token) {
//				List<Collaborator> collaboratorList = collaboratorServiceInf.getNoteCollaborators(token, noteId);
//				
//				return collaboratorList != null
//						? ResponseEntity.status(HttpStatus.CREATED)
//								.body(new Responses("all note collaborators are", 200, collaboratorList))
//						: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responses("Something went wrong", 400));
//			
	}
}
