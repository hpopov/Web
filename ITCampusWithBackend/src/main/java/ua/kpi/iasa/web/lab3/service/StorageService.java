package ua.kpi.iasa.web.lab3.service;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

import org.springframework.core.io.Resource;

import ua.kpi.iasa.web.lab3.exception.FileStorageException;
import ua.kpi.iasa.web.lab3.model.FileModel;

public interface StorageService {

    void store(InputStream file, FileModel fileModel) throws FileStorageException;

    Resource loadAsResource(FileModel fileModel) throws FileStorageException;

    void copy(Path sourcePath, FileModel fileModel) throws FileStorageException;

    void delete(FileModel fileModel) throws FileStorageException;

    default void deleteAll(Collection<FileModel> fileModels) throws FileStorageException {
        
        for(FileModel fileModel : fileModels) {
            delete(fileModel);
        }
    }
}
