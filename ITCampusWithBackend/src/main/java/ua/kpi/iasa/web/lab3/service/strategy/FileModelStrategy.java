package ua.kpi.iasa.web.lab3.service.strategy;

import ua.kpi.iasa.web.lab3.model.FileModel;

public interface FileModelStrategy {

    FileModel makeFileModel(String fileName);
}
