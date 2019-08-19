package ua.kpi.iasa.web.lab3.service;

import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.UserModel;

public interface UserService {

	UserModel getUserByUsername(String username);

	boolean updateUserById(UserData user);

}