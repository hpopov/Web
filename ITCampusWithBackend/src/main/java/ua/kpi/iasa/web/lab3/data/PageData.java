package ua.kpi.iasa.web.lab3.data;

import java.util.List;

public class PageData {
	
	private UserData user;
	private PersonalInfoData personalInfo;
	private List<SubjectData> subjects;
	private List<ProjectData> projects;
	private UserData currentUser;

	public UserData getUser() {
		return user;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

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

	public UserData getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserData currentUser) {
		this.currentUser = currentUser;
	}
	
	
}

//user: UserData;
//personalInfo: PersonalInfoData;
//subjects: SubjectData[];
//projects: ProjectData[];
//currentUser?: UserData;
