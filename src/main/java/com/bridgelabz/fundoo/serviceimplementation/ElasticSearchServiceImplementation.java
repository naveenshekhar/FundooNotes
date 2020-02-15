package com.bridgelabz.fundoo.serviceimplementation;

import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.service.ElasticSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ElasticSearchServiceImplementation implements ElasticSearchService {

	private static final String INDEX = "fundoonotes";

	private static final String TYPE = "notes";

	@Autowired
	private RestHighLevelClient restHighLevelClient;

	@Autowired
	private ObjectMapper objectMapper;

	@SuppressWarnings("rawtypes")
	public String createData(Notes notes) {
		log.info("creating the notes into elastic search");
		try {
			Long id = notes.getNoteId();
			String noteId = String.valueOf(id);
			Map getData = objectMapper.convertValue(notes, Map.class);
			IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, noteId).source(getData);
			IndexResponse respo = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
			return respo.getResult().name();
		} catch (Exception e) {
			log.error("error occured while creating notes into elasticsearch" + e.getMessage());
		}
		return null;
	}

	public Map<String, Object> searchNotes(String title) {
		log.info("searching notes by id from elast");
		SearchRequest searchRequest = new SearchRequest();
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", title);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(queryBuilder);
		searchRequest.source(sourceBuilder);
		SearchResponse searchResponse = null;
		try {
			searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
			searchResponse.getHits().forEach(s -> System.out.println(s.getSourceAsMap()));
			return searchResponse.getHits().getAt(0).getSourceAsMap();
		} catch (Exception e) {
			log.error("error" + "[" + e.getMessage() + "]" + "occured while searching the notes");
		}
		return null;
	}

	@Override
	public Map<String, Object> updateById(String id, Notes notes) {
		log.info("updating the notes in elastic search");
		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, id).fetchSource(true);
		try {
			String getDataType = objectMapper.writeValueAsString(notes);
			updateRequest.doc(getDataType, XContentType.JSON);
			UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
			return updateResponse.getGetResult().sourceAsMap();
		} catch (Exception e) {
			log.error("error" + "[" + e.getMessage() + "]" + "occured while updating the notes");
		}
		return null;
	}

	@Override
	public void deleteById(String id) {
		log.info("deleting notes by id in elasticSearch");
		try {
			DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
			DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
			log.info("deleting response in elastic search " + deleteResponse);
		} catch (Exception e) {
			log.error("error occured while deleting file" + e.getMessage());
		}
	}
}
