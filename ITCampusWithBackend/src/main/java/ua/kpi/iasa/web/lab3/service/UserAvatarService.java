package ua.kpi.iasa.web.lab3.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import ua.kpi.iasa.web.lab3.model.AvatarModel;

public interface UserAvatarService {

    AvatarModel createDefaultUserAvatar(String username);

    AvatarModel changeUserAvatar(String username, MultipartFile file);

    Resource getUserAvatarImage(String username);
    
    void deleteUserAvatarWithImage(String username);
}
