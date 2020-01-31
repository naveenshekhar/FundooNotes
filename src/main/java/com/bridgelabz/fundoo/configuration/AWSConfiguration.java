package com.bridgelabz.fundoo.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {

	@Value("${aws.accesskey}")
	private String accessKey;

	@Value("${aws.keyid}")
	private String secretKey;

	@Value("${aws.region}")
	private String region;

	@Bean
	public AmazonS3 s3client() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
		return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	}
}

/*
 * @Value("${aws.accesskey}") private String accessKey;
 * 
 * @Value("${aws.keyid}") private String secretKey;
 * 
 * @Value("${cloud.aws.region}") private String region;
 * 
 * @Bean public AmazonS3 awsS3Client() { BasicAWSCredentials awsCredentials =
 * new BasicAWSCredentials(accessKey, secretKey); return
 * AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
 * .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build(); }
 */
