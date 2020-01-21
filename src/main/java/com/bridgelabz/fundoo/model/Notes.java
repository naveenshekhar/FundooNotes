package com.bridgelabz.fundoo.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "note")
@Getter
@Setter
public class Notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long noteId;
	
	@Column(name = "title", nullable = false)
	@NotEmpty(message = "Title can't be empty..!!")
	private String title;
	
	@Column(name = "description", nullable = true)
	@NotEmpty(message = "your note is empty")
	private String description;
	
	@Column(name = "userId", nullable = true)
	@NotEmpty(message = "enter user id")
	private int  userId;
	
	@Column(name = "isPinned", nullable = true, columnDefinition = "boolean default false" )
	private boolean isPinned;
	
	@Column(name = "isArchived", nullable = true, columnDefinition = "boolean default false")
	private boolean isArchived;
	
	@Column(name = "creationTime", nullable = false)
	@NotEmpty(message = "enter note creation time")
	private Timestamp creationTime;
	
	@Column(name = "reminder", nullable = true, columnDefinition = "boolean default false")
	@NotEmpty(message = "Enter if you want any reminder")
	private Date reminder;
	
	@Column(name = "color", nullable = true, columnDefinition = "boolean default false")
	@NotEmpty(message = "enter note creation time")
	private String color;

	

	
	
	
//	private int collaboratorId; ///will add after creating collaborator table
//	private int labelId;
//	private String image;
}
