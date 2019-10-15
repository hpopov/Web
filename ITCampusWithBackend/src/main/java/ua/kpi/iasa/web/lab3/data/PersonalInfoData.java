package ua.kpi.iasa.web.lab3.data;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoData {

	private PublicUserData user;
	private String phoneNumber;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String city;
	private String education;
	private String faculty;
	private Integer[] educationYear = new Integer[2];
	private List<String[]> skills;
	private List<String[]> languages;

	@JsonIgnore
	public void setEducationBeginYear(Integer beginYear) {
		educationYear[0] = beginYear;
	}

	@JsonIgnore
	public void setEducationEndYear(Integer endYear) {
		educationYear[1] = endYear;
	}

	@JsonIgnore
	public Integer getEducationBeginYear() {
		return educationYear[0];
	}

	@JsonIgnore
	public Integer getEducationEndYear() {
		return educationYear[1];
	}
}