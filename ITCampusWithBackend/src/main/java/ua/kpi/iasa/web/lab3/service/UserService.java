package ua.kpi.iasa.web.lab3.service;

import java.util.List;

import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.data.RegistrationData;
import ua.kpi.iasa.web.lab3.model.UserModel;

public interface UserService {

    UserModel getUserByUsername(String username);

    UserModel getUserWithDetailsByUsername(String username);

    UserModel getUserWithProjectsByUsername(String username);

    List<UserModel> getAllUsers();

    UserModel createRegularUser(RegistrationData registrationData);

    UserModel updateUser(PublicUserData publicUserData);

    void deleteUser(String username);
}