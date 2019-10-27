package ua.kpi.iasa.web.lab3.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import ua.kpi.iasa.web.lab3.model.FileModel;

public interface FileService {

    Resource loadFileByFileName(String fileName);

    FileModel generateFileModelFromMultipartFile(MultipartFile file);

    FileModel generateFileModelFromFileName(String fileName);

    boolean isFileAnSupportedImage(FileModel file);
}
