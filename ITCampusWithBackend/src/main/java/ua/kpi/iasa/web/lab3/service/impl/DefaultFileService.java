package ua.kpi.iasa.web.lab3.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Sets;

import ua.kpi.iasa.web.lab3.exception.FileStorageException;
import ua.kpi.iasa.web.lab3.exception.NotFoundException;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.FileConverter;
import ua.kpi.iasa.web.lab3.model.FileModel;
import ua.kpi.iasa.web.lab3.service.FileService;
import ua.kpi.iasa.web.lab3.service.StorageService;
import ua.kpi.iasa.web.lab3.service.strategy.FileModelStrategy;

@Service
public class DefaultFileService implements FileService {

    private static final String FILE_WAS_NOT_FOUND = "File %s was not found";

    @Autowired
    private StorageService storageService;

    @Autowired
    private FileConverter fileConverter;
    @Autowired
    @Qualifier("randomFileModelStrategy")
    private FileModelStrategy fileModelStrategy;

    private final Set<String> supportedImageExtensions;

    public DefaultFileService() {
        supportedImageExtensions = Sets.newHashSet("png", "jpg", "jpeg", "gif", "tiff");
    }

    @Override
    public Resource loadFileByFileName(String fileName) {
        try {
            return storageService.loadAsResource(fileConverter.dataToModel(fileName));
        } catch (FileStorageException ex) {
            throw new NotFoundException(String.format(FILE_WAS_NOT_FOUND, fileName), ex);
        }
    }

    @Override
    public FileModel generateFileModelFromMultipartFile(MultipartFile file) {
        return fileModelStrategy.makeFileModel(file.getOriginalFilename());
    }

    @Override
    public FileModel generateFileModelFromFileName(String fileName) {
        return fileModelStrategy.makeFileModel(fileName);
    }

    @Override
    public boolean isFileAnSupportedImage(FileModel file) {
        return supportedImageExtensions.contains(file.getExtension().toLowerCase());
    }
}
