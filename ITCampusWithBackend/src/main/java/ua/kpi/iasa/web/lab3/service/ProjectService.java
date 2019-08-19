package ua.kpi.iasa.web.lab3.service;

import java.util.List;

import ua.kpi.iasa.web.lab3.model.ProjectModel;

public interface ProjectService {

	List<ProjectModel> getProjectsByUserId(int userId);

}