package ua.kpi.iasa.web.lab3.model;

import java.util.Map;

public class PersonalInfoModel {

	private int userId;
	private String phoneNumber;
	private String dateOfBirth;
	private String city;
	private String education;
	private String faculty;
	private int startEducationYear;
	private int finishEducationYear;
	private Map<String, String> skills;
	private Map<String, String> languages;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
	//	public Date getDateOfBirth() {
	//		return dateOfBirth;
	//	}

	//	public void setDateOfBirth(Date dateOfBirth) {
	//		this.dateOfBirth = dateOfBirth;
	//	}

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

	public int getStartEducationYear() {
		return startEducationYear;
	}

	public void setStartEducationYear(int startEducationYear) {
		this.startEducationYear = startEducationYear;
	}

	public int getFinishEducationYear() {
		return finishEducationYear;
	}

	public void setFinishEducationYear(int finishEducationYear) {
		this.finishEducationYear = finishEducationYear;
	}

	public Map<String, String> getSkills() {
		return skills;
	}

	public void setSkills(Map<String, String> skills) {
		this.skills = skills;
	}

	public Map<String, String> getLanguages() {
		return languages;
	}

	public void setLanguages(Map<String, String> languages) {
		this.languages = languages;
	}

}
