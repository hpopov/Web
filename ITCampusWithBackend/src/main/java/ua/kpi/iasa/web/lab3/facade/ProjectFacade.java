package ua.kpi.iasa.web.lab3.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.ProjectConverter;
import ua.kpi.iasa.web.lab3.service.ProjectService;

@Service
public class ProjectFacade {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectConverter projectConverter;

	public List<ProjectData> getProjectsByUsername(String username) {
		return Converter.mapToList(projectConverter::modelToData, projectService.getProjectsByUsername(username));
	}
	
}
