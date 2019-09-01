package ua.kpi.iasa.web.lab3.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.ProjectModel;
import ua.kpi.iasa.web.lab3.service.ProjectService;

@Service
public class ProjectFacade {
	
	@Autowired
	ProjectService projectService;

	public List<ProjectData> getProjectsByUsername(String username) {
		return projectService.getProjectsByUsername(username).stream().map(this::toData)
				.collect(Collectors.toList());
	}
	
	private ProjectData toData(ProjectModel model) {
		ProjectData data = new ProjectData();
		data.setDescription(model.getDescription());
		data.setHeader(model.getHeader());
		data.setImageName(model.getImageName());
		return data;
	}
}
