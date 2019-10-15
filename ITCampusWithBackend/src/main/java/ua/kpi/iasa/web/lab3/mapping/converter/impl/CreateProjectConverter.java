package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.CreateProjectData;
import ua.kpi.iasa.web.lab3.mapping.converter.DataToModelConverter;
import ua.kpi.iasa.web.lab3.model.ProjectModel;

@Component
public class CreateProjectConverter implements DataToModelConverter<ProjectModel, CreateProjectData> {

    @Override
    public ProjectModel dataToModel(@NonNull CreateProjectData data) {
        final ProjectModel model = new ProjectModel();
        model.setDescription(data.getDescription());
        model.setHeader(data.getHeader());
        return model;
    }
}
