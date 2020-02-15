package com.bridgelabz.fundoo.service;

import java.io.IOException;
import java.util.Map;

import com.bridgelabz.fundoo.model.Notes;

public interface ElasticSearchService 
{
	String createData(Notes notes);
	Map<String,Object> searchNotes(String title);
	Map<String, Object> updateById(String id,Notes notes) throws IOException;
	void deleteById(String id);

}
