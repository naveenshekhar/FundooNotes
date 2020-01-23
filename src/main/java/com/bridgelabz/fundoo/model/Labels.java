package com.bridgelabz.fundoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Lables")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Labels {

	@Id
	@Column(name = "labelId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labelId;

	@Column(name = "labelName", nullable = false)
	@NotEmpty(message = "Enter first name first")
	private String labelName;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User LabelUser;

//	@JsonIgnore
//	@ManyToMany(mappedBy = "labels")
//	private List<Notes> noteList;
}
