package com.mini.project.last.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mini.project.last.Dto.DashboardResponse;
import com.mini.project.last.Dto.EnquiryForm;
import com.mini.project.last.Dto.EnquirySearchCriteria;
import com.mini.project.last.Dto.DashboardResponse.DashboardResponseBuilder;
import com.mini.project.last.Model.EnquiryEntity;
import com.mini.project.last.Model.EnquiryStatusEntity;
import com.mini.project.last.Model.UserEntity;
import com.mini.project.last.Repo.CoursesRepo;
import com.mini.project.last.Repo.EnquiryRepo;
import com.mini.project.last.Repo.EnquiryStatusRepo;
import com.mini.project.last.Repo.UserRepo;
import com.mini.project.last.Utils.stringMesg;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EnquiryRepo enquiryRepo;
	@Autowired
	private CoursesRepo coursesRepo;
	@Autowired
	private EnquiryStatusRepo repo;
	
	@Autowired
	private UserRepo userRepo;
	

	@Override
	public List<String> getAllCourseName() {
		
		return coursesRepo.getAllByCourseName();
	}

	@Override
	public List<String> getAllStatusName() {
		
		return repo.getAllByStatusName();
	}

	@Override
	public DashboardResponse getDashboardData(Integer userId) {
		
		Integer data = enquiryRepo.getDataById(userId);
		Integer enrolledData = enquiryRepo.getEnrolledData(userId);
		Integer lostData = enquiryRepo.getLostData(userId);
		
		DashboardResponse enquiries = DashboardResponse.builder()
						 .totalEnquiries(data)
						 .enrolled(enrolledData)
						 .lost(lostData)
						 .build();

		return enquiries;
	}

	@Override
	public String upsertEnquiry(EnquiryForm enquiryForm) {
		
		Integer userId = Optional.ofNullable((Integer) session.getAttribute("userid"))
                .orElse(null);

		
		EnquiryEntity enqEnt = EnquiryEntity.builder()
				.studentName(enquiryForm.getStudentName())
				.studentContactNumber(enquiryForm.getContactNo())
				.classMode(enquiryForm.getClassMode())
				.courseName(enquiryForm.getCourseName())
				.enquiryStatus(enquiryForm.getEnquiryStatus())	
				.build();
		
		UserEntity ent = userRepo.findById(userId).orElseThrow();
		
		enqEnt.setUser(ent);
		
		
		enquiryRepo.save(enqEnt);
		
		return stringMesg.STUS_STR;
		
	}

	@Override
	public List<EnquiryEntity> getEnquiries(EnquirySearchCriteria enquirySearchCriteria, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnquiryForm getEnquiryData(Integer enquiryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnquiryEntity> getAll(EnquirySearchCriteria criteria ,Integer id) {

	//	List<EnquiryEntity> list = enquiryRepo.get(id);
		
		EnquiryEntity build = EnquiryEntity.builder()
					.classMode(criteria.getClassMode())
					.courseName(criteria.getCourseName())
					.enquiryStatus(criteria.getEnqStatus()).build();
		
		List<EnquiryEntity> list = enquiryRepo.findAll(Example.of(build));
				
		return list;
	}


	
}
