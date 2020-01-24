package com.bridgelabz.fundoo.model;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "noteLabel")
public class NotesLable {

	@Column(name = "note_id", nullable = false)
	@ManyToOne
	private long noteId;

	@Column(name = "label_id", nullable = false)
	@ManyToOne
	private long labelId;

}
