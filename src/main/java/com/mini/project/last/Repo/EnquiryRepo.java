package com.mini.project.last.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mini.project.last.Model.EnquiryEntity;


public interface EnquiryRepo extends JpaRepository<EnquiryEntity, Integer>{

	@Query("SELECT COUNT(e) FROM EnquiryEntity e WHERE e.user.userId = :userId")
	Integer getDataById(Integer userId);

	@Query("SELECT COUNT(e) FROM EnquiryEntity e WHERE e.user.userId = :userId AND e.enquiryStatus = 'ENROLLED'")
	Integer getEnrolledData(Integer userId);

	@Query("SELECT COUNT(e) FROM EnquiryEntity e WHERE e.user.userId = :userId AND e.enquiryStatus = 'LOST'")
	Integer getLostData(Integer userId);
	
	@Query("SELECT s FROM EnquiryEntity s WHERE s.user.userId = :userId")
	List<EnquiryEntity> get(Integer userId);


}
