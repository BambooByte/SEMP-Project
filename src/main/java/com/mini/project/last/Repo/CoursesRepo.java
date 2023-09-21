package com.mini.project.last.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mini.project.last.Model.CoursesEntity;

public interface CoursesRepo extends JpaRepository<CoursesEntity, Integer> {

	@Query("SELECT c.courseName FROM CoursesEntity c")
	List<String> getAllByCourseName();
	

}
