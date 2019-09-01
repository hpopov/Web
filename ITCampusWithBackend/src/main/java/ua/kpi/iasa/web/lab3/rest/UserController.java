package ua.kpi.iasa.web.lab3.rest;

import java.util.List;

import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.data.RegistrationData;

public interface UserController {
	boolean registerUser(RegistrationData registrationData);
	List<PublicUserData> getAllUsers();
	PublicUserData getUserByLogin( String username);	
	boolean updatePublicUserByLogin(String username, PublicUserData publicUserData);
	boolean deleteUser(String username);
}
