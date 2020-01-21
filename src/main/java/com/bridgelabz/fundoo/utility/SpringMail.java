package com.bridgelabz.fundoo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SpringMail {

	@Autowired
	JavaMailSender javamailsender;
	
	SimpleMailMessage simpleMsg = new SimpleMailMessage();

	public void sendMail(String email, String response) {
		System.out.println("111");
			simpleMsg.setTo(email);
			System.out.println("121");
			simpleMsg.setSubject("Verify mail");
			System.out.println("131");
			simpleMsg.setText(response);
			System.out.println("141");
			javamailsender.send(simpleMsg);
			System.out.println("151");
	}
}
