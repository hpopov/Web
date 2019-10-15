package ua.kpi.iasa.web.lab3.service.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import ua.kpi.iasa.web.lab3.config.properties.UserProperties;
import ua.kpi.iasa.web.lab3.dao.AvatarDao;
import ua.kpi.iasa.web.lab3.exception.FileStorageException;
import ua.kpi.iasa.web.lab3.model.AvatarModel;
import ua.kpi.iasa.web.lab3.model.FilePathModel;
import ua.kpi.iasa.web.lab3.service.StorageService;
import ua.kpi.iasa.web.lab3.service.UserAvatarService;
import ua.kpi.iasa.web.lab3.service.strategy.FilePathStrategy;

@Slf4j
@Service
public class DefaultUserAvatarService implements UserAvatarService {

    private static final String FAILED_TO_DELETE_OLD_USER_AVATAR = "Unable to delete old avatar for %s during changing it!";
    private static final String FAILED_TO_CHANGE_USER_AVATAR = "Failed to change avatar for user %s";
    private static final String FAILED_TO_RETRIEVE_USER_AVATAR = "Failed to retrieve avatar for %s";
    private static final String FAILED_TO_CREATE_DEFAULT_USER_AVATAR = "Failed to create default avatar for user %s";
    private static final String FAILED_TO_DELETE_USER_AVATAR = "Failed to delete avatar for user %s";

    @Autowired
    private AvatarDao avatarDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private UserProperties userProperties;
    @Autowired
    @Qualifier("randomFilePathStrategy")
    private FilePathStrategy filePathStrategy;

    private Path defaultAvatarImagePath;

    @PostConstruct
    private void initialize() {
        defaultAvatarImagePath = Paths.get(userProperties.getDefaultAvatarPath());
    }

    @Override
    public AvatarModel createDefaultUserAvatar(String username) {
        final String avatarFileName = defaultAvatarImagePath.getFileName().toString();
        final FilePathModel targetFilePath = filePathStrategy.makeFilePath(avatarFileName);
        try {
            storageService.copy(defaultAvatarImagePath, targetFilePath);
        } catch (FileStorageException e) {
            throw new RuntimeException(String.format(FAILED_TO_CREATE_DEFAULT_USER_AVATAR, username), e);
        }
        AvatarModel avatar = new AvatarModel();
        avatar.setUsername(username);
        avatar.setAvatarImage(targetFilePath);
        return avatarDao.save(avatar);
    }

    @Transactional
    @Override
    public AvatarModel changeUserAvatar(String username, MultipartFile file) {
        final AvatarModel avatar = findUserAvatar(username);
        final FilePathModel oldAvatarFilePath = avatar.getAvatarImage();
        final FilePathModel newAvatarFilePath = filePathStrategy.makeFilePath(file.getOriginalFilename());
        try {
            storageService.store(file.getInputStream(), newAvatarFilePath);
        } catch (IOException e) {
            throw new RuntimeException(String.format(FAILED_TO_CHANGE_USER_AVATAR, username), e);
        }
        avatar.setAvatarImage(newAvatarFilePath);
        try {
            storageService.delete(oldAvatarFilePath);
        } catch (FileStorageException e) {
            log.error(String.format(FAILED_TO_DELETE_OLD_USER_AVATAR, username), e);
        }
        return avatar;
    }

    @Override
    public Resource getUserAvatarImage(String username) {
        final FilePathModel imageFilePath = findUserAvatar(username).getAvatarImage();
        try {
            return storageService.loadAsResource(imageFilePath);
        } catch (FileStorageException e) {
            throw new RuntimeException(String.format(FAILED_TO_RETRIEVE_USER_AVATAR, username), e);
        }
    }

    private AvatarModel findUserAvatar(String username) {
        return avatarDao.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteUserAvatarWithImage(String username) {
        final AvatarModel userAvatar = findUserAvatar(username);

        try {
            storageService.delete(userAvatar.getAvatarImage());
        } catch (FileStorageException e) {
            throw new RuntimeException(String.format(FAILED_TO_DELETE_USER_AVATAR, username), e);
        }
    }
}
