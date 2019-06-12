package ua.kpi.iasa.web.lab3.dao;

import java.util.List;

import ua.kpi.iasa.web.lab3.model.ProjectModel;

public interface ProjectDao {

	List<ProjectModel> getAllProjects() throws DaoException;
}
