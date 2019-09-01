package ua.kpi.iasa.web.lab3.service;

import java.util.List;
import java.util.Optional;

import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.data.RegistrationData;
import ua.kpi.iasa.web.lab3.model.UserModel;

public interface UserService {

	UserModel getUserByUsername(String username);
	List<UserModel> getAllUsers();
	UserModel createRegularUser(RegistrationData registrationData);
	Optional<UserModel> updateUser(PublicUserData publicUserData);
	void deleteUser(String username);
}