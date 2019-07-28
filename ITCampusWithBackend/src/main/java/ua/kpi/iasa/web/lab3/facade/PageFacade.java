package ua.kpi.iasa.web.lab3.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.data.PageData;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.data.SubjectData;
import ua.kpi.iasa.web.lab3.data.UserData;

@Service
public class PageFacade {
	
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private SubjectFacade subjectFacade;
	@Autowired
	private ProjectFacade projectFacade;
	@Autowired
	private PersonalInfoFacade personalInfoFacade;
	
	
	public PageData makePageDataFromUsernameAndToken(String userName, String token) {
		PageData pageData = new PageData();
		UserData user = userFacade.getUserDataByUsername(userName);
		List<SubjectData> subjects = subjectFacade.getSubjectsByUser(user);
		List<ProjectData> projects = projectFacade.getProjectsByUser(user);
		PersonalInfoData personalInfo = personalInfoFacade.getPersonalInfoByUser(user);
		pageData.setPersonalInfo(personalInfo);
		pageData.setProjects(projects);
		pageData.setSubjects(subjects);
		pageData.setUser(user);
		return pageData;
	}
}
