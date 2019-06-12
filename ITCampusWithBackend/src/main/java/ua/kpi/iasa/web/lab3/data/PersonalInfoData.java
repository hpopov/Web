package ua.kpi.iasa.web.lab3.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonalInfoData {

	private String phoneNumber;
	private String dateOfBirth;
	private String city;
	private String education;
	private String faculty;
	private int[] educationYear;
	private List<String[]> skills;
	private List<String[]> languages;
	
	public PersonalInfoData() {
		this.educationYear = new int[2];
		skills = new ArrayList<>();
		languages = new ArrayList<>();
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

	public int[] getEducationYear() {
		return educationYear;
	}

	public void setEducationBeginYear(int beginYear) {
		educationYear[0] = beginYear;
	}

	public void setEducationEndYear(int endYear) {
		educationYear[1] = endYear;
	}
	
	public List<String[]> getSkills() {
		return skills;
	}
	
	public void addSkill(String skillName, String skillLevel) {
		skills.add(new String[]{skillName, skillLevel});
	}

	public List<String[]> getLanguages() {
		return languages;
	}	
	
	public void addLanguage(String langName, String langLevel) {
		languages.add(new String[]{langName, langLevel});
	}
	
}