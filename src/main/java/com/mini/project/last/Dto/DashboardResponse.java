package com.mini.project.last.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Builder
public class DashboardResponse {

	private Integer totalEnquiries;
	
	private Integer enrolled;
	
	private Integer lost;

	public Integer getTotalEnquiries() {
		return totalEnquiries;
	}

	public void setTotalEnquiries(Integer totalEnquiries) {
		this.totalEnquiries = totalEnquiries;
	}

	public Integer getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(Integer enrolled) {
		this.enrolled = enrolled;
	}

	public Integer getLost() {
		return lost;
	}

	public void setLost(Integer lost) {
		this.lost = lost;
	}
	
	
}
