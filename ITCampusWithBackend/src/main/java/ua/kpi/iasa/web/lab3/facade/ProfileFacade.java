package ua.kpi.iasa.web.lab3.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.data.ProfileData;
import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.data.SubjectData;

@Service
public class ProfileFacade {

    @Autowired
    private SubjectFacade subjectFacade;
    @Autowired
    private ProjectFacade projectFacade;
    @Autowired
    private PersonalInfoFacade personalInfoFacade;

    private ProfileData makeProfileDataFromUsername(String username) {
        ProfileData pageData = new ProfileData();
        List<SubjectData> subjects = subjectFacade.getSubjectsByUsername(username);
        List<ProjectData> projects = projectFacade.getProjectsByUsername(username);
        PersonalInfoData personalInfo = personalInfoFacade.getPersonalInfoByUsername(username);
        pageData.setPersonalInfo(personalInfo);
        pageData.setProjects(projects);
        pageData.setSubjects(subjects);
        return pageData;
    }

    public ProfileData getProfileByUsername(String login) {
        return makeProfileDataFromUsername(login);
    }

    public boolean updateProfile(ProfileData profileData) {
//		profileData.get
        return false;// TODO
    }
}
