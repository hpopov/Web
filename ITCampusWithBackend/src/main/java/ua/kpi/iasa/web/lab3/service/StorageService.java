package ua.kpi.iasa.web.lab3.service;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

import org.springframework.core.io.Resource;

import ua.kpi.iasa.web.lab3.exception.FileStorageException;
import ua.kpi.iasa.web.lab3.model.FilePathModel;

public interface StorageService {

    void store(InputStream file, FilePathModel filePathModel) throws FileStorageException;

    Resource loadAsResource(FilePathModel filePathModel) throws FileStorageException;

    void copy(Path sourcePath, FilePathModel filePathModel) throws FileStorageException;

    void delete(FilePathModel filePathModel) throws FileStorageException;

    default void deleteAll(Collection<FilePathModel> filePathModels) throws FileStorageException {
        
        for(FilePathModel filePathModel : filePathModels) {
            delete(filePathModel);
        }
    }
}
