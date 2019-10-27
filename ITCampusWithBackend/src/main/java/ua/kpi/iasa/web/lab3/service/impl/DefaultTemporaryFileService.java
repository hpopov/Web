package ua.kpi.iasa.web.lab3.service.impl;

import java.io.IOException;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.kpi.iasa.web.lab3.dao.TemporaryFileDao;
import ua.kpi.iasa.web.lab3.exception.FileStorageException;
import ua.kpi.iasa.web.lab3.model.FileModel;
import ua.kpi.iasa.web.lab3.model.TemporaryFileModel;
import ua.kpi.iasa.web.lab3.service.FileService;
import ua.kpi.iasa.web.lab3.service.StorageService;
import ua.kpi.iasa.web.lab3.service.TemporaryFileService;

@Service
public class DefaultTemporaryFileService implements TemporaryFileService {

    private static final String UNABLE_TO_STORE_TEMP_FILE = "Unable to store temporary file %s";
    private static final String UNABLE_TO_DELETE_OLD_TEMP_FILE = "Unable to delete old temporary file %s";
    @Autowired
    private FileService fileService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private TemporaryFileDao temporaryFileDao;

    @Transactional
    @Override
    public TemporaryFileModel uploadTemporaryFileForUser(MultipartFile file, String username) {
        final TemporaryFileModel userTemporaryFile = temporaryFileDao.findByUsername(username)
                .orElseGet(TemporaryFileModel::new);
        final FileModel oldFileModel = userTemporaryFile.getFile();
        userTemporaryFile.setFile(fileService.generateFileModelFromMultipartFile(file));
        storeTemporaryFile(file, userTemporaryFile.getFile());
        if (Objects.nonNull(oldFileModel)) {
            deleteOldTemporaryFile(oldFileModel);
        } else {
            userTemporaryFile.setUsername(username);
            return temporaryFileDao.save(userTemporaryFile);
        }
        return userTemporaryFile;
    }

    private void storeTemporaryFile(MultipartFile file, FileModel fileModel) {
        try {
            storageService.store(file.getInputStream(), fileModel);
        } catch (IOException ex) {
            throw new RuntimeException(String.format(UNABLE_TO_STORE_TEMP_FILE, fileModel.getFileName()), ex);
        }
    }

    private void deleteOldTemporaryFile(FileModel oldFileModel) {
        try {
            storageService.delete(oldFileModel);
        } catch (FileStorageException ex) {
            throw new RuntimeException(String.format(UNABLE_TO_DELETE_OLD_TEMP_FILE, oldFileModel.getFileName()), ex);
        }
    }

    @Override
    public TemporaryFileModel getTemporaryFileById(Integer id) {
        return temporaryFileDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void removeFileFromTemporalSection(TemporaryFileModel temporaryFile) {
        temporaryFileDao.delete(temporaryFile);
    }
}
