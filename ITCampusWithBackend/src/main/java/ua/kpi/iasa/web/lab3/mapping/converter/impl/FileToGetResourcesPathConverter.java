package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.converter.ModelToDataConverter;
import ua.kpi.iasa.web.lab3.model.FileModel;

@Component
public class FileToGetResourcesPathConverter implements ModelToDataConverter<FileModel, String> {

    private static final String RESOURCES_REST_PATH = "/resources/";

    @Autowired
    private FileConverter fileConverter;

    @Override
    public String modelToData(@NonNull FileModel model) {
        return RESOURCES_REST_PATH + fileConverter.modelToData(model);
    }

}
