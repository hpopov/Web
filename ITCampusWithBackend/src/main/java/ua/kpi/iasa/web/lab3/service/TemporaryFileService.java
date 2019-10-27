package ua.kpi.iasa.web.lab3.service;

import org.springframework.web.multipart.MultipartFile;

import ua.kpi.iasa.web.lab3.model.TemporaryFileModel;

public interface TemporaryFileService {

    public TemporaryFileModel uploadTemporaryFileForUser(MultipartFile file, String username);

    public TemporaryFileModel getTemporaryFileById(Integer temporaryFileId);

    public void removeFileFromTemporalSection(TemporaryFileModel temporaryFile);
}
