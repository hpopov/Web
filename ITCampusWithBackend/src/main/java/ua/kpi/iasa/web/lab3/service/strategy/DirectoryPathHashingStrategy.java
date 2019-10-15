package ua.kpi.iasa.web.lab3.service.strategy;

import java.nio.file.Path;

import ua.kpi.iasa.web.lab3.model.FilePathModel;

public interface DirectoryPathHashingStrategy {
    Path hashDirectoryPath(FilePathModel filePathModel);
}
