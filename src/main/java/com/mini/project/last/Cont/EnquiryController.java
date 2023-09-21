package com.mini.project.last.Cont;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mini.project.last.Dto.DashboardResponse;
import com.mini.project.last.Dto.EnquiryForm;
import com.mini.project.last.Dto.EnquirySearchCriteria;
import com.mini.project.last.Model.EnquiryEntity;
import com.mini.project.last.Repo.EnquiryRepo;
import com.mini.project.last.Service.EnquiryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
		@Autowired
		private EnquiryService enquiryService;
		
		@Autowired
		private EnquiryRepo enquiryRepo;
		
		@Autowired
		private HttpSession session;

	 @GetMapping("/dashboard")
	    public String showDashboardPage(Model model) {
		 
		Integer attribute = (Integer) session.getAttribute("userid");
		 
		 DashboardResponse data = enquiryService.getDashboardData(attribute);
		 	
		 
		 model.addAttribute("totalEnquiries", data.getTotalEnquiries());		 
		 model.addAttribute("enrolled", data.getEnrolled());
		 model.addAttribute("lost", data.getLost());
	    
			 
		 
	    	return "dashboard";
	    }
	 
	 
	 @GetMapping("/add")
	    public String showAddEnquiryPage(@ModelAttribute("enquiryForm") EnquiryForm enquiryForm ,Model model) {
		 
		 model.addAttribute("enquiryForm", new EnquiryForm());
		 init(model);
		 return "addEnquiry";
		 
	 }
	
	 @PostMapping("/add")
	    public String addEnquiry(@ModelAttribute("enquiryForm") EnquiryForm enquiryForm ,Model model) {
		
		 String mesg = enquiryService.upsertEnquiry(enquiryForm);
		 
		 model.addAttribute("mesg", mesg);
		 
		 
		 init(model);
		 
		 return "addEnquiry";
		 
	 }


	private void init(Model model) {
		
		List<String> courseNames = enquiryService.getAllCourseName();
		 List<String> statusNames = enquiryService.getAllStatusName();
		
		 
		 model.addAttribute("statusNames", statusNames);
		 model.addAttribute("courseNames", courseNames);
		
	}
	
	 
	 @GetMapping("/viewEnquiry")
	    public String showEnquiriesPages(Model model) {
		 init(model);
		 return "viewEnquiry";
	 }
	
	 
	 @PostMapping("/enquiry")
	 @ResponseBody
	    public  List<EnquiryEntity> showEnquiriesPage(
	    		@RequestParam("courseName") String courseName,
	    		@RequestParam("enqStatus") String enqStatus,
	    		@RequestParam("classMode") String classMode,		
	    		Model model) {
		 
		 init(model);
		 
		 Integer uid = (Integer) session.getAttribute("userid");
		 
		EnquirySearchCriteria criteria = EnquirySearchCriteria.builder()
								.classMode(classMode)
								.courseName(courseName)	
								.enqStatus(enqStatus).build();
		
		 
		 return enquiryService.getAll(criteria, uid);
		 
	 }
	 
	 @GetMapping("/data")
		@ResponseBody
		public String getData(@RequestParam String country, @RequestParam String state, @RequestParam String city) {
		  return "Selected Country: " + country + ", State: " + state + ", City: " + city;
		}
	 
	@GetMapping("/logout")
	public String logOut() {
		
		session.invalidate();
		
		return "index";
	}
	
}
