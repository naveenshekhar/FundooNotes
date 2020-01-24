package com.bridgelabz.fundoo.service;


import com.bridgelabz.fundoo.dto.DeleteLabelDto;
import com.bridgelabz.fundoo.dto.LabelDto;
import com.bridgelabz.fundoo.model.Labels;

public interface LabelService {     

	Labels create(LabelDto label, String token);

	Labels delete(DeleteLabelDto deletelabelDto, String token);
	
	
	
//	public Labels labelMapToNote(LabelDto label, String token, long noteId);
//
//	public Labels updateLabel(String token, long noteId, long labelId);
//
//	public Labels addLabel(String token, long noteId, long labelId);
//
	public Labels getAllLabels(String token);
//

}
