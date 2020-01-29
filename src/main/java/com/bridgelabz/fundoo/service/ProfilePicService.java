package com.bridgelabz.fundoo.service;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.model.ProfilePic;

@Component
public interface ProfilePicService
{
	
	ProfilePic storeObjectInS3(MultipartFile file,String originalFileName, String contentType,String token);
	
	void deleteProfilePic(String key);

}
