package com.mini.project.last.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mini.project.last.Model.EnquiryStatusEntity;

public interface EnquiryStatusRepo extends JpaRepository<EnquiryStatusEntity, Integer>{

	@Query("SELECT e.statusName FROM EnquiryStatusEntity e ")
	List<String> getAllByStatusName();


}
