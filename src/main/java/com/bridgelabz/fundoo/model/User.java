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
	private long id;

	@Column(name = "first_name", nullable = false)
	@NotEmpty(message = "Enter first name first")
	private String first_name;

	@Column(name = "last_name", nullable = true)
	private String last_name;

	@Column(name = "phone_number",nullable = false, unique = true)
	@NotEmpty(message = "enter phone number")
	private String phone_number;

	@Column(name = "email", nullable = false, unique = true)
	@NotEmpty(message = "Email cannot be empty..!!")
	private String email;

	@Column(name = "password", nullable = false)
	@NotEmpty(message = "password cannot be empty..!!")
	private String password;
}
