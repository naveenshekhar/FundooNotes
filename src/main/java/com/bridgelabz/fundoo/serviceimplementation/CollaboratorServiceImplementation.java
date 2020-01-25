package com.bridgelabz.fundoo.serviceimplementation;

import com.bridgelabz.fundoo.model.Collaborator;
import com.bridgelabz.fundoo.service.CollaboratorService;

public class CollaboratorServiceImplementation implements CollaboratorService{

	@Override
	public Collaborator addCollaborator(String token, long noteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collaborator removeCollaborator(String token, long noteId) {
		// TODO Auto-generated method stub
		return null;
	}
	

//@Service
//public class CollaboratorServiceImplementation implements CollaboratorServiceInf {
//
//	@Autowired
//	private JwtGenerator jwtGenerator;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private NoteRepository noteRepository;
//
//	@Autowired
//	private CollaboratorRepository collaboratorRepository;
//
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
//
//	@Override
//	public Collaborator addCollaborator(CollaboratorDto collaboratorDto, String token, long noteId) {
//		try {
//			Collaborator collaborator = new Collaborator();
//			Notes note = noteRepository.findById(noteId);
//			Collaborator collaboratorDB = collaboratorRepository.findOneByEmail(collaboratorDto.getEmail(), noteId);
//			if (note != null && collaboratorDB == null) {
////				collaborator.setEmail(collaboratorDB.getEmail());
//				BeanUtils.copyProperties(collaboratorDto, collaborator);
//				collaborator.setNote(note);
//				collaboratorRepository.addCollaborator(collaborator.getId(), collaborator.getEmail(), noteId);
//				return collaborator;
//			} else {
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	@Override
//	public Collaborator deleteCollaborator(long collaboratorId, String token, long noteId) {
//		try {
//			long userId = jwtGenerator.parse(token);
//			User user = userRepository.findById(userId);
//			if (user != null) {
//				Notes note = noteRepository.findById(noteId);
//				if (note != null) {
//					Collaborator collaborator = collaboratorRepository.findById(collaboratorId);
//					if (collaborator != null) {
//						collaboratorRepository.deleteCollaborator(collaboratorId, noteId);
//						return collaborator;
//					}
//				}
//			} else {
//				return null;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public List<Collaborator> getNoteCollaborators(String token, long noteId) {
//		try {
//			long userId = jwtGenerator.parse(token);
//			if (userId != 0) {
//				List<Notes> note = noteRepository.searchAllNotesByNoteId(userId, noteId);
//				if (note != null) {
//					return collaboratorRepository.getAllNoteCollaborators(noteId);
//				}
//			} else {
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}


}
