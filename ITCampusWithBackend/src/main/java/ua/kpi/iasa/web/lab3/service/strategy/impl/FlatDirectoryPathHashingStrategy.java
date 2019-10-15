package ua.kpi.iasa.web.lab3.service.strategy.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.mapping.converter.impl.FilePathConverter;
import ua.kpi.iasa.web.lab3.model.FilePathModel;
import ua.kpi.iasa.web.lab3.service.strategy.DirectoryPathHashingStrategy;

@Component
public class FlatDirectoryPathHashingStrategy implements DirectoryPathHashingStrategy {

    @Autowired
    private FilePathConverter filePathConverter;

    @Override
    public Path hashDirectoryPath(FilePathModel filePathModel) {
        return Paths.get(filePathConverter.modelToData(filePathModel));
    }
}
