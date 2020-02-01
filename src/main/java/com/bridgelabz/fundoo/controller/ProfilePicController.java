package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.model.ProfilePic;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.ProfilePicService;

import io.swagger.annotations.ApiOperation;

@RestController("ProfilePicController")
@RequestMapping("/profilepic")
public class ProfilePicController {
	@Autowired
	ProfilePicService profilePicService;

	@PostMapping("/uploadProfilePic/(token)")
	@ApiOperation(value = "API to upload profile pic")
	public ResponseEntity<Responce> addProfilePic(@ModelAttribute MultipartFile file,
			@RequestHeader("token") String token) {
		System.out.println("1");
		ProfilePic profile = profilePicService.storeObjectInS3(file, file.getOriginalFilename(), file.getContentType(),
				token);
		System.out.println("1.1 :" + profile.getUserLabel());
		if (profile.getUserLabel() != null) {
			System.out.println("2");
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Responce("profile pic added Successfully", 200, profile));
		} else {
			System.out.println("3");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce("failed to update", 400, profile));
		}

	}

//	@Autowired
//	private ProfilePicService profilePicService;
//
//	@PostMapping("/uploadprofilepic")
//	@ApiOperation(value = "Api to upload profile pic of User for Fundoonotes", response = Responses.class)
//	public ResponseEntity<Responses> addProfilePic(@ModelAttribute MultipartFile file,
//			@RequestHeader("token") String token) {
//
//		ProfilePic profile = profilePicService.storeObjectInS3(file, file.getOriginalFilename(), file.getContentType(),
//				token);
//		return profile.getUserLabel() != null
//				? ResponseEntity.status(HttpStatus.OK).body(new Responses("profile added succussefully", 200, profile))
//				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responses("something went Wrong ", 400));
//	}
//
	@PutMapping("/updateProfilePic")
	@ApiOperation(value = "Api to update profile pic of User", response = Responce.class)
	public ResponseEntity<Responce> updateProfilePic(@ModelAttribute MultipartFile file,
			@RequestHeader("token") String token) {
		ProfilePic profile = profilePicService.updateProfilePic(file, file.getOriginalFilename(), file.getContentType(),
				token);
		return profile != null
				? ResponseEntity.status(HttpStatus.ACCEPTED)
						.body(new Responce("Profile Pic update Sucessfully!!!", 200))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce("Something went wrong!!!", 400));
	}

}
