package ua.kpi.iasa.web.lab3.converter;

import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.model.ProjectModel;

@Component
public class ProjectConverter implements Converter<ProjectModel, ProjectData> {

	@Override
	public ProjectData modelToData(ProjectModel model) {
		final ProjectData data = new ProjectData();
		data.setId(model.getProjectId());
		data.setHeader(model.getHeader());
		data.setDescription(model.getDescription());
		data.setImageName(model.getImageName());
		return data;
	}

	@Override
	public ProjectModel populateDataToModel(ProjectModel model, ProjectData data) {
		if (model == null) {
			model = new ProjectModel();
		}
		model.setDescription(data.getDescription());
		model.setHeader(data.getHeader());
		model.setImageName(data.getImageName());
		model.setProjectId(data.getId());
		return model;
	}


}
