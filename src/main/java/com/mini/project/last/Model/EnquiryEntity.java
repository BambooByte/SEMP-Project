package com.mini.project.last.Model;
	import java.time.LocalDate;

	import org.hibernate.annotations.CreationTimestamp;

	import jakarta.persistence.CascadeType;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import lombok.AllArgsConstructor;
	import lombok.Builder;
	import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

	@Entity
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public class EnquiryEntity {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
		
		private String studentName;
		
		private String studentContactNumber;
		
		private String classMode;
		
		private String courseName;
		
		private String enquiryStatus;
		
		@CreationTimestamp
		private LocalDate createdDate;
		
		
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "userId")
		private UserEntity user;

		
		
		
		

}
