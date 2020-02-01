package com.bridgelabz.fundoo.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bridgelabz.fundoo.configuration.AWSConfiguration;
import com.bridgelabz.fundoo.model.ProfilePic;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.ProfilePicRepository;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.ProfilePicService;
import com.bridgelabz.fundoo.utility.JwtGenerator;

@Service
public class profilePicServiceImplementation implements ProfilePicService {

	@Autowired
	private ProfilePicRepository profilePicRepository;

	@Autowired
	private JwtGenerator jwtGenerator;

	@Autowired
	private UserRepository userRepository;

	@Value("${aws.bucket.name}")
	private String bucketName;

	@Autowired
	private AmazonS3 amazonClient;

	@Autowired
	private AWSConfiguration s3;

	@Override
	public ProfilePic storeObjectInS3(MultipartFile file, String fileName, String contentType, String token) {
		try {
			System.out.println("inside service 11");
			Long userId = jwtGenerator.parse(token);
			User user = userRepository.findbyId(userId);
			if (user != null) {
				ProfilePic profile = new ProfilePic(fileName, user);
				ObjectMetadata objectMetadata = new ObjectMetadata();
				objectMetadata.setContentType(contentType);
				objectMetadata.setContentLength(file.getSize());
				amazonClient.putObject(bucketName, fileName, file.getInputStream(), objectMetadata);
				profilePicRepository.save(profile);
				System.out.println("profile :" + profile);
				return profile;
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteProfilePic(String key) {
		try {
			amazonClient.deleteObject(bucketName, key);
		} catch (AmazonServiceException serviceException) {
			serviceException.printStackTrace();
		} catch (AmazonClientException exception) {
			exception.printStackTrace();
		}

	}

}
