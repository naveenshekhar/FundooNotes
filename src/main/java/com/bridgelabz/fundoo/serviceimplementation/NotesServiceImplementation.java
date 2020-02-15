package com.bridgelabz.fundoo.serviceimplementation;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.NotesRepository;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.ElasticSearchService;
import com.bridgelabz.fundoo.service.NotesService;
import com.bridgelabz.fundoo.utility.JwtGenerator;

@Service
public class NotesServiceImplementation implements NotesService {

	Notes notes;
	@Autowired
	private NotesRepository notesrepo;

	@Autowired
	private UserRepository users;

	@Autowired
	private JwtGenerator tokenGenerator;

	@Autowired
	private ElasticSearchService elasticSearchService;

	Notes notesModel = new Notes();

	Date date = new Date();
	Long time = date.getTime();
	Timestamp timeStamp = new Timestamp(time);

	@Override
	public Notes create(NotesDto noteDto, String token) {
		try {
			long parseToken = tokenGenerator.parse(token);
			User user = users.findbyId(parseToken);
			if (user != null) {
				notes = new Notes();
				notes.setTitle(noteDto.getTitle());
				notes.setDescription(noteDto.getDescription());
				notes.setUser(user);
				notesrepo.insertData(notes.getColor(), notes.getTitle(), notes.getDescription(), notes.isPinned(),
						notes.isArchived(), timeStamp, parseToken);
				return notes;

			}
		} catch (Exception e) {
			System.out.println(e);

		}
		return null;
	}

	@Override
	public Notes update(long noteId, NotesDto notesDto, String token) {
		try {
			long ptoken = tokenGenerator.parse(token);
			User user = users.findbyId(ptoken);
			Notes note = notesrepo.findbyId(noteId);
			if (user != null) {
				if (note != null) {
					notesrepo.update(note.getTitle(), note.getDescription(), noteId);
					return note;
				}
				return null;
			} else {
				return null;
			}
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean pin(String token, Long noteId) {
		try {
			long parseToken = tokenGenerator.parse(token);
			User user = users.findbyId(parseToken);
			Notes note = notesrepo.findbyId(noteId);
			if (user != null) {
				if (note != null) {
					if (note.isPinned()) {
						notesrepo.isPinned(false, noteId);
						return true;
					} else {
						notesrepo.isPinned(true, noteId);
						return true;
					}
				}
			}

		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isArchive(Long noteId, String token) {
		System.out.println("inside service");
		try {
			long parsToken = tokenGenerator.parse(token);
			User user = users.findbyId(parsToken);
			Notes note = notesrepo.findbyId(noteId);

			if (user != null) {
				if (note != null) {
					if (note.isArchived()) {
						notesrepo.isArchived(false, noteId);
						return true;
					} else if (!note.isArchived()) {
						notesrepo.isArchived(true, noteId);
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean trash(long noteId, String token) {

		try {
			long parseToken = tokenGenerator.parse(token);
			User user = users.findbyId(parseToken);
			Notes note = notesrepo.findbyId(noteId);

			if (user != null) {
				if (note != null) {
					if (note.isTrashed()) {
						notesrepo.isTrashed(false, noteId);
						return true;
					} else if (!note.isTrashed()) {
						notesrepo.isTrashed(true, noteId);
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Long delete(Long noteId, String token) {

		long parseToken;
		try {
			parseToken = tokenGenerator.parse(token);
			User user = users.findbyId(parseToken);
			Notes note = notesrepo.findbyId(noteId);
			if (user != null) {
				if (note != null) {
					notesrepo.delete(noteId);
					return noteId;
				} else {
					return null;
				}
			}
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Notes searchByTitle(String title) {

		notes = notesrepo.searchByTitle(title);
		if (notes != null) {
			return notes;
		} else
			return null;
	}

	@Override
	public List<Notes> getAllNotes(String token) {
		List<Notes> notes = notesrepo.findAllNotes(token);
		if (notes != null) {
			return notes;
		}
		return null;
	}

	@Override
	public Map<String, Object> elasticSearch(String word) {
		// TODO Auto-generated method stub
		return null;
	}

}
