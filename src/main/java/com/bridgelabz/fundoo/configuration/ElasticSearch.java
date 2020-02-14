package com.bridgelabz.fundoo.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;


public class ElasticSearch{


	@Bean
	public RestHighLevelClient elasticsearchClient() {
		
		return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));
	}

}
