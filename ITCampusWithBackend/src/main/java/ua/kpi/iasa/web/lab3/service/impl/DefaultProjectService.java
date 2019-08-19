package ua.kpi.iasa.web.lab3.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.ProjectDao;
import ua.kpi.iasa.web.lab3.model.ProjectModel;
import ua.kpi.iasa.web.lab3.service.ProjectService;
import ua.kpi.iasa.web.lab3.service.exception.EntityNotFoundException;

@Service
public class DefaultProjectService implements ProjectService {
	
	@Autowired
	@Qualifier("projectDao")
	private ProjectDao projectDao;

	@Override
	public List<ProjectModel> getProjectsByUserId(int userId) {
		try {
			return projectDao.getAllProjects()
					.stream().filter(model-> model.getUserId() == userId)
					.collect(Collectors.toList());
		} catch (DaoException e) {
			throw new EntityNotFoundException(e);
		}
	}
}
