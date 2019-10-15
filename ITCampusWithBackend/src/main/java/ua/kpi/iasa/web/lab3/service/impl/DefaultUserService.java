package ua.kpi.iasa.web.lab3.service.impl;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.dao.PersonalInfoDao;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.data.RegistrationData;
import ua.kpi.iasa.web.lab3.exception.FileStorageException;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.RegistrationConverter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.PublicUserPopulator;
import ua.kpi.iasa.web.lab3.model.AuthorityType;
import ua.kpi.iasa.web.lab3.model.FilePathModel;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.model.ProjectModel;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.AuthorityService;
import ua.kpi.iasa.web.lab3.service.StorageService;
import ua.kpi.iasa.web.lab3.service.UserAvatarService;
import ua.kpi.iasa.web.lab3.service.UserService;

@Service
public class DefaultUserService implements UserService {

    private static final String FAILED_TO_DELETE_USER_PROJECT_IMAGES = "Failed to delete project images for user %s";

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RegistrationConverter registrationConverter;
    @Autowired
    private PublicUserPopulator publicUserPopulator;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PersonalInfoDao personalInfoDao;
    @Autowired
    private UserAvatarService userAvatarService;
    @Autowired
    private StorageService storageService;

    private Collection<AuthorityType> regularUserAuthorities;

    @PostConstruct
    public void initialize() {
        regularUserAuthorities = EnumSet.of(AuthorityType.ROLE_USER);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userDao.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserModel getUserWithDetailsByUsername(String username) {
        return userDao.findByUsernameFetchUserDetailsModel(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserModel getUserWithProjectsByUsername(String username) {
        return userDao.findByUsernameFetchProjects(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public UserModel createRegularUser(RegistrationData registrationData) {
        final UserModel user = registrationConverter.dataToModel(registrationData);
        final UserDetailsModel userDetails = user.getUserDetails();
        userDetails.setAuthorities(regularUserAuthorities.stream().map(authorityService::getOrCreateAuthorityForType)
                .collect(Collectors.toSet()));
        createUser(user);
        return user;
    }

    private void createUser(UserModel user) {
        user = userDao.save(user);
        final PersonalInfoModel personalInfo = new PersonalInfoModel();
        personalInfo.setUser(user);
        personalInfoDao.save(personalInfo);
        userAvatarService.createDefaultUserAvatar(user.getUsername());
    }

    @Override
    @Transactional
    public UserModel updateUser(PublicUserData publicUserData) {
        final UserModel user = getUserByUsername(publicUserData.getLogin());
        return publicUserPopulator.populate(publicUserData, user);
    }

    @Transactional
    @Override
    public void deleteUser(String username) {
        userAvatarService.deleteUserAvatarWithImage(username);
        final UserModel user = getUserWithProjectsByUsername(username);
        final Collection<FilePathModel> projectImages = user.getProjects().stream().map(ProjectModel::getProjectImage)
                .collect(Collectors.toList());
        try {
            storageService.deleteAll(projectImages);
        } catch (FileStorageException e) {
            throw new RuntimeException(String.format(FAILED_TO_DELETE_USER_PROJECT_IMAGES, username), e);
        }
        userDao.delete(user);
    }
}
