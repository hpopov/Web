package ua.kpi.iasa.web.lab3.data;

import java.util.List;

public class ProfileData {
	
	private PersonalInfoData personalInfo;
	private List<SubjectData> subjects;
	private List<ProjectData> projects;

	public PersonalInfoData getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfoData personalInfo) {
		this.personalInfo = personalInfo;
	}

	public List<SubjectData> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectData> subjects) {
		this.subjects = subjects;
	}

	public List<ProjectData> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectData> projects) {
		this.projects = projects;
	}
	
	
}
