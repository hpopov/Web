package ua.kpi.iasa.web.lab3.service.strategy;

import ua.kpi.iasa.web.lab3.model.FilePathModel;

public interface FilePathStrategy {

    FilePathModel makeFilePath(String fileName);
}
