package com.mini.project.last.Service;

import java.util.List;

import com.mini.project.last.Dto.DashboardResponse;
import com.mini.project.last.Dto.EnquiryForm;
import com.mini.project.last.Dto.EnquirySearchCriteria;
import com.mini.project.last.Model.EnquiryEntity;


public interface EnquiryService {
	
	List<String> getAllCourseName();
	
	List<String> getAllStatusName();
	
	DashboardResponse getDashboardData(Integer userId);
	
	String upsertEnquiry(EnquiryForm enquiryForm);
	
	List<EnquiryEntity> getEnquiries(EnquirySearchCriteria enquirySearchCriteria, Integer userId);
	
	EnquiryForm getEnquiryData(Integer enquiryId);
	
	List<EnquiryEntity> getAll(EnquirySearchCriteria criteria,Integer id);
	
}
