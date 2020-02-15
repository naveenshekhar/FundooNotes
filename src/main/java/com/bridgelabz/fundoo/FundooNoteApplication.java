/**
 * @author  : Naveen Shekhar
 * @version :1.0
 * @category:Spring
 * @purpose :Main implementation class which serves two purpose in a spring boot application: Configuration and bootstrapping.
 * @Name    :FundooNoteApplication 
 */
package com.bridgelabz.fundoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundooNoteApplication {
	public static void main(String[] args) {
		SpringApplication.run(FundooNoteApplication.class, args);
	}

}
