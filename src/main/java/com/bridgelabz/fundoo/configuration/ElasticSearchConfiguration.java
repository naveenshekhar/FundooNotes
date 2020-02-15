package com.bridgelabz.fundoo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfiguration {
	 @Value("${elasticsearch.port:9300}") 
	    public int port;
	public int getPort() {
		return port;
	}


}
