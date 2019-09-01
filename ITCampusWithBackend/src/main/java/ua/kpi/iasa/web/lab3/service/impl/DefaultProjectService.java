package ua.kpi.iasa.web.lab3.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.ProjectDao;
import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.model.ProjectModel;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.ProjectService;
import ua.kpi.iasa.web.lab3.service.UserService;
import ua.kpi.iasa.web.lab3.service.exception.EntityNotFoundException;

@Service
public class DefaultProjectService extends LoggableComponent implements ProjectService {
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private UserService userService;

	@Override
	public List<ProjectModel> getProjectsByUsername(String username) {
		try {
			final UserModel user = userService.getUserByUsername(username);	
			return projectDao.getAllUserProjects(user);
		} catch (DaoException e) {
			throw new EntityNotFoundException(e);///
		}
	}

	@Override
	public boolean createUserProject(String username, ProjectData project) {
		final UserModel user = userService.getUserByUsername(username);
		try {
			projectDao.createUserProject(user, project);
			return true;
		} catch (DaoException e) {
			log.error("Unable to create user project", e);
			return false;
		}
	}

	@Override
	public boolean updateUserProject(String username, ProjectData project)
			throws EntityNotFoundException {
		final UserModel user = userService.getUserByUsername(username);
		try {
			Optional<ProjectModel> updatedProject = projectDao.updateUserProjectById(user, project);
			return updatedProject.isPresent();
		} catch (DaoException e) {
			log.error("Unable to update user project", e);
			return false;
		}
	}
}
