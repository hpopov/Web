package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.model.FileModel;

@Component
public class FileConverter implements Converter<FileModel, String> {

    @Override
    public String modelToData(@NonNull FileModel model) {
        return model.getFileName().toString() + "." + model.getExtension();
    }

    @Override
    public FileModel dataToModel(@NonNull String data) {
        final String[] nameAndExtension = data.split("[.]", 2);
        FileModel model = new FileModel();
        model.setFileName(UUID.fromString(nameAndExtension[0]));
        model.setExtension(nameAndExtension[1]);
        return model;
    }
}
