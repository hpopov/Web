package ua.kpi.iasa.web.lab3.service.strategy.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.mapping.converter.impl.FileConverter;
import ua.kpi.iasa.web.lab3.model.FileModel;
import ua.kpi.iasa.web.lab3.service.strategy.DirectoryPathHashingStrategy;

@Component
public class FlatDirectoryPathHashingStrategy implements DirectoryPathHashingStrategy {

    @Autowired
    private FileConverter fileConverter;

    @Override
    public Path hashDirectoryPath(FileModel fileModel) {
        return Paths.get(fileConverter.modelToData(fileModel));
    }
}
