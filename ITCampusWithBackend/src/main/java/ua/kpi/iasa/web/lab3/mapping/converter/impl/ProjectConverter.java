package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.mapping.converter.ModelToDataConverter;
import ua.kpi.iasa.web.lab3.model.ProjectModel;

@Component
public class ProjectConverter implements ModelToDataConverter<ProjectModel, ProjectData> {

    @Autowired
    private FileToGetResourcesPathConverter fileToGetResourcesPathConverter;

    @Override
    public ProjectData modelToData(@NonNull ProjectModel model) {
        final ProjectData data = new ProjectData();
        data.setId(model.getId());
        data.setHeader(model.getHeader());
        data.setDescription(model.getDescription());
        data.setImageUrl(fileToGetResourcesPathConverter.modelToData(model.getProjectImage()));
        return data;
    }
}
