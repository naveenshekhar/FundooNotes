package com.bridgelabz.fundoo.model;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "collaborator")
@Getter
@Setter
public class Collaborator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collaborator_id")
	private long collaboratorId;

	@Column(name = "collaborator_email", nullable = true)
	@NotEmpty(message = "Email can't be empty")
	private String collaboratorEmail;

	
	@NotEmpty
	@ManyToOne
	@JoinColumn(name = "id")
	private Notes noteId;


	private String Email;
	
}
