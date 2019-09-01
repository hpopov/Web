package ua.kpi.iasa.web.lab3.service;

import java.util.List;

import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.model.ProjectModel;
import ua.kpi.iasa.web.lab3.service.exception.EntityNotFoundException;

public interface ProjectService {

	List<ProjectModel> getProjectsByUsername(String username);
	//making 2 separate methods for update and delete, because user can have many projects
	boolean createUserProject(String username, ProjectData project);
	boolean updateUserProject(String username, ProjectData project) throws EntityNotFoundException;

}