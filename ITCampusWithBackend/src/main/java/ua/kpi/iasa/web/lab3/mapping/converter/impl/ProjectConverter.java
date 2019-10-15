package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.mapping.converter.ModelToDataConverter;
import ua.kpi.iasa.web.lab3.model.ProjectModel;

@Component
public class ProjectConverter implements ModelToDataConverter<ProjectModel, ProjectData> {

    private static final String RESOURCES_REST_PATH = "/resources/";

    @Autowired
    private FilePathConverter filePathConverter;

    @Override
    public ProjectData modelToData(@NonNull ProjectModel model) {
        final ProjectData data = new ProjectData();
        data.setId(model.getId());
        data.setHeader(model.getHeader());
        data.setDescription(model.getDescription());
        data.setImageUrl(RESOURCES_REST_PATH + filePathConverter.modelToData(model.getProjectImage()));
        return data;
    }
}
