package ua.kpi.iasa.web.lab3.service.impl;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import ua.kpi.iasa.web.lab3.dao.ProjectDao;
import ua.kpi.iasa.web.lab3.data.CreateProjectData;
import ua.kpi.iasa.web.lab3.data.UpdateProjectData;
import ua.kpi.iasa.web.lab3.exception.FileStorageException;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.CreateProjectConverter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.UpdateProjectPopulator;
import ua.kpi.iasa.web.lab3.model.FileModel;
import ua.kpi.iasa.web.lab3.model.ProjectModel;
import ua.kpi.iasa.web.lab3.model.TemporaryFileModel;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.FileService;
import ua.kpi.iasa.web.lab3.service.ProjectService;
import ua.kpi.iasa.web.lab3.service.StorageService;
import ua.kpi.iasa.web.lab3.service.TemporaryFileService;
import ua.kpi.iasa.web.lab3.service.UserService;

@Service
@Slf4j
public class DefaultProjectService implements ProjectService {

    private static final String IMAGE_EXTENSION_IS_UNSUPPORTED = "Image extension %s for file %s is unsupported";

    private static final String UNABLE_TO_REMOVE_PROJECT_IMAGE_FROM_STORAGE = "Unable to remove project image %s from storage";

    private static final String UNABLE_TO_STORE_PROJECT_IMAGE = "Unable to store user project image %s";

    private static final String FAILED_TO_DELETE_OLD_PROJECT_IMAGE = "Failed to delete old project image %s";

    @Autowired
    private UserService userService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private UpdateProjectPopulator updateProjectPopulator;
    @Autowired
    private CreateProjectConverter createProjectConverter;
    @Autowired
    private FileService fileService;
    @Autowired
    private TemporaryFileService temporaryFileService;

    @Override
    public List<ProjectModel> getProjectsByUsername(String username) {
        final UserModel user = userService.getUserByUsername(username);
        return projectDao.findAllByUserId(user.getId());
    }

    @Transactional
    @Override
    public ProjectModel createUserProject(String username, CreateProjectData project) {
        final UserModel user = userService.getUserByUsername(username);
        final ProjectModel projectModel = createProjectConverter.dataToModel(project);
        projectModel.setUserId(user.getId());
        final TemporaryFileModel temporaryFile = temporaryFileService
                .getTemporaryFileById(project.getTemporaryFileId());
        checkFileIsValidImage(temporaryFile.getFile());
        projectModel.setProjectImage(temporaryFile.getFile());
        temporaryFileService.removeFileFromTemporalSection(temporaryFile);
        return projectDao.save(projectModel);
    }

    private void checkFileIsValidImage(FileModel file) {
        if (false == fileService.isFileAnSupportedImage(file)) {
            throw new IllegalArgumentException(
                    String.format(IMAGE_EXTENSION_IS_UNSUPPORTED, file.getExtension(), file.getFileName()));
        }
    }

    @Override
    public ProjectModel updateUserProject(String username, UpdateProjectData project) throws EntityNotFoundException {
        ProjectModel projectModel = getProjectByUsernameAndProjectId(username, project.getId());
        projectModel = updateProjectPopulator.populate(project, projectModel);
        return projectDao.save(projectModel);
    }

    private ProjectModel getProjectByUsernameAndProjectId(String username, Integer projectId) {
        final UserModel user = userService.getUserByUsername(username);
        ProjectModel projectModel = projectDao.findByIdAndUserId(projectId, user.getId())
                .orElseThrow(EntityNotFoundException::new);
        return projectModel;
    }

    @Transactional
    @Override
    public ProjectModel updateUserProjectImage(String username, Integer projectId, MultipartFile image)
            throws EntityNotFoundException {
        final ProjectModel projectModel = getProjectByUsernameAndProjectId(username, projectId);
        final FileModel oldProjectImage = projectModel.getProjectImage();
        final FileModel newProjectImage = fileService.generateFileModelFromMultipartFile(image);

        storeUserProjectImage(image, newProjectImage);
        projectModel.setProjectImage(newProjectImage);
        tryToDeleteOldProjectImage(oldProjectImage);
        return projectModel;
    }

    private void tryToDeleteOldProjectImage(final FileModel oldProjectImage) {
        try {
            storageService.delete(oldProjectImage);
        } catch (FileStorageException ex) {
            log.error(String.format(FAILED_TO_DELETE_OLD_PROJECT_IMAGE, oldProjectImage.getFileName().toString()), ex);
        }
    }

    private void storeUserProjectImage(MultipartFile image, FileModel projectImageFilePath) {
        try {
            storageService.store(image.getInputStream(), projectImageFilePath);
        } catch (IOException ex) {
            throw new RuntimeException(String.format(UNABLE_TO_STORE_PROJECT_IMAGE, image.getOriginalFilename()), ex);
        }
    }

    @Override
    @Transactional
    public void deleteUserProjectById(String username, Integer projectId) throws EntityNotFoundException {
        final UserModel user = userService.getUserByUsername(username);
        ProjectModel projectModel = projectDao.findByIdAndUserId(projectId, user.getId())
                .orElseThrow(EntityNotFoundException::new);
        deleteUserProjectImage(projectModel.getProjectImage());
        projectDao.delete(projectModel);
    }

    private void deleteUserProjectImage(FileModel projectImagePathModel) {
        try {
            storageService.delete(projectImagePathModel);
        } catch (FileStorageException ex) {
            throw new RuntimeException(
                    String.format(UNABLE_TO_REMOVE_PROJECT_IMAGE_FROM_STORAGE, projectImagePathModel.getFileName()),
                    ex);
        }
    }
}
