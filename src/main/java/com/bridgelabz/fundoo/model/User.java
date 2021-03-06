/****************************************
 * @Author  : Naveen Shekhar
 * @version : 1.0
 * @ purpose: Model class to interact with User
 * @Date    : 08:01:2020
 * @File    : User.java 
 ****************************************/

package com.bridgelabz.fundoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
@Getter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "firstName", nullable = false)
	@NotEmpty(message = "Enter first name first")
	private String firstName;

	@Column(name = "lastName", nullable = true)
	private String lastName;

	@Column(name = "phoneNumber", nullable = false)
	@NotEmpty(message = "enter phone number")
	private String phoneNumber;

	@Column(name = "email", nullable = false)
	@NotEmpty(message = "Email cannot be empty..!!")
	private String email;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	@NotEmpty(message = "password cannot be empty..!!")
	private String password;

	@Column(name = "isVerified", columnDefinition = "boolean default false")
	private boolean isVerified;

	@Column(name = "isUserAvailable", columnDefinition = "boolean default false")
	private boolean isUserAvailable;
}
