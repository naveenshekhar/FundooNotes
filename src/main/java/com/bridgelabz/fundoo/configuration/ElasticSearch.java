package com.bridgelabz.fundoo.configuration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;


@Configuration
public class ElasticSearch extends AbstractElasticsearchConfiguration {

	@Override
	public RestHighLevelClient elasticsearchClient() {
		return RestClients.create(ClientConfiguration.create("localhost:9200")).rest();
	}

}
