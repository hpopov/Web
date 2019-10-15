package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.UpdateProjectData;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.ProjectModel;

@Component
public class UpdateProjectPopulator implements Populator<ProjectModel, UpdateProjectData> {

    @Override
    public ProjectModel populate(@NonNull UpdateProjectData data, @NonNull ProjectModel model) {
        model.setDescription(data.getDescription());
        model.setHeader(data.getHeader());
        model.setId(data.getId());
        return model;
    }
}
