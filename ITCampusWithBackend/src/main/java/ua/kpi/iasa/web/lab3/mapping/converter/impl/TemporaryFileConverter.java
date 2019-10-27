package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.TemporaryFileData;
import ua.kpi.iasa.web.lab3.mapping.converter.ModelToDataConverter;
import ua.kpi.iasa.web.lab3.model.TemporaryFileModel;

@Component
public class TemporaryFileConverter implements ModelToDataConverter<TemporaryFileModel, TemporaryFileData> {

    @Autowired
    private FileToGetResourcesPathConverter fileToGetResourcesPathConverter;

    @Override
    public TemporaryFileData modelToData(@NonNull TemporaryFileModel model) {
        return TemporaryFileData.builder()// @formatter:off
            .id(model.getId())
            .requestUrl(fileToGetResourcesPathConverter.modelToData(model.getFile()))
            .build();// @formatter:on
    }
}
