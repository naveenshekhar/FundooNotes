package com.bridgelabz.fundoo.model;

import java.sql.Timestamp;

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
@Table(name = "note")
@Getter
@Setter
public class Notes {
	
	public Timestamp getCreationTime() {
		return creationTime;
	}





	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

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
	
	@Column(name = "isPinned", nullable = true, columnDefinition = "boolean default false" )
	private boolean isPinned;
	
	@Column(name = "isArchived", nullable = true, columnDefinition = "boolean default false")
	private boolean isArchived;
	
	@Column(name = "isTrashed", nullable = true, columnDefinition = "boolean default false")
	private boolean isTrashed;
	
	@Column(name = "creationTime", nullable = false)
	@NotEmpty(message = "enter note creation time")
	private Timestamp creationTime;
	
	@Column(name = "reminder", nullable = true, columnDefinition = "boolean default false")
	@NotEmpty(message = "Enter if you want any reminder")
	private boolean reminder;
	
	@Column(name = "color", nullable = true)
	@NotEmpty(message = "enter the color")
	private String color;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
}
