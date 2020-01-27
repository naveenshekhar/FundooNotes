package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.ProfilePicService;

import io.swagger.annotations.ApiOperation;

@RestController("ProfilePicController")
@RequestMapping("/profilepic")
public class ProfilePicController {

	@Autowired
	ProfilePicService profilePicService;

	@PostMapping("/uploadProfilePic")
	@ApiOperation(value = "API to upload profile pic")
	public ResponseEntity<Responce> addProfilePic(@ModelAttribute MultipartFile, @RequestHeader("token") String token) 
	{
		profilePicService profile=profilePicService.storeObjectInS3(file.getOriginalFileName(),file.getContentType(),token);
		
		
		
		return null;
	}

}
