package com.mini.project.last.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CoursesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer courseId;
	
	private String courseName;
}
