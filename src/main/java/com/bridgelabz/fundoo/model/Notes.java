package com.bridgelabz.fundoo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private int noteId;
	
	
	private String title;
	private String description;
	private int userId;
	private boolean isPinned;
	private boolean isArchived;
	private Date creationTime;
	private String color;
//	private int collaboratorId;
//	private String image;
}
