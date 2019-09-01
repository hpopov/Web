package ua.kpi.iasa.web.lab3.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalInfoData {

	private PublicUserData user;
	private String phoneNumber;
	private String dateOfBirth;
	private String city;
	private String education;
	private String faculty;
	private Integer[] educationYear;
	private List<String[]> skills;
	private List<String[]> languages;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public PersonalInfoData(@JsonProperty PublicUserData user,
			@JsonProperty String phoneNumber, @JsonProperty String dateOfBirth,
			@JsonProperty String city, @JsonProperty String education, @JsonProperty String faculty,
			@JsonProperty Integer[] educationYear, @JsonProperty List<String[]> skills,
			@JsonProperty List<String[]> languages) {
		this.user = user;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.city = city;
		this.education = education;
		this.faculty = faculty;
		this.educationYear = educationYear;
		this.skills = skills;
		this.languages = languages;
	}

	public PersonalInfoData() {
		this.educationYear = new Integer[2];
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public Integer[] getEducationYear() {
		return educationYear;
	}

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
	
	public List<String[]> getSkills() {
		return skills;
	}

	public List<String[]> getLanguages() {
		return languages;
	}

	public void setSkills(List<String[]> skills) {
		this.skills = skills;
	}

	public void setLanguages(List<String[]> languages) {
		this.languages = languages;
	}

	public PublicUserData getUser() {
		return user;
	}

	public void setUser(PublicUserData user) {
		this.user = user;
	}
}