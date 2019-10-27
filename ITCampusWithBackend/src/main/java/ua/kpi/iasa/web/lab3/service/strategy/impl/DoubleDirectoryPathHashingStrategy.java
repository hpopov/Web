package ua.kpi.iasa.web.lab3.service.strategy.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.mapping.converter.impl.FileConverter;
import ua.kpi.iasa.web.lab3.model.FileModel;
import ua.kpi.iasa.web.lab3.service.strategy.DirectoryPathHashingStrategy;

@Component
public class DoubleDirectoryPathHashingStrategy implements DirectoryPathHashingStrategy {

    private static final String DIRECTORY_NAME_FORMAT = "%02x";
    private static final int mask = 255;
    private static final int shift = 8;

    @Autowired
    private FileConverter fileConverter;

    @Override
    public Path hashDirectoryPath(FileModel fileModel) {
        final int hash = fileModel.getFileName().hashCode();
        final String firstDir = getDirectoryStringName(hash & mask);
        final String secondDir = getDirectoryStringName((hash >> shift) & mask);
        return Paths.get(firstDir, secondDir, fileConverter.modelToData(fileModel));
    }

    private String getDirectoryStringName(int name) {
        return String.format(DIRECTORY_NAME_FORMAT, name);
    }

}
