package ua.kpi.iasa.web.lab3.dao;

import java.util.List;
import java.util.Optional;

import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.model.ProjectModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

public interface ProjectDao {

	List<ProjectModel> getAllUserProjects(UserModel user) throws DaoException;
	ProjectModel createUserProject(UserModel user, ProjectData project) throws DaoException;
	Optional<ProjectModel> updateUserProjectById(UserModel user, ProjectData project) throws DaoException;
}
