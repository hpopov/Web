package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.model.FilePathModel;

@Component
public class FilePathConverter implements Converter<FilePathModel, String> {

    @Override
    public String modelToData(@NonNull FilePathModel model) {
        return model.getFileName().toString() + "." + model.getExtension();
    }

    @Override
    public FilePathModel dataToModel(@NonNull String data) {
        final String[] nameAndExtension = data.split("[.]", 2);
        FilePathModel model = new FilePathModel();
        model.setFileName(UUID.fromString(nameAndExtension[0]));
        model.setExtension(nameAndExtension[1]);
        return model;
    }
}
