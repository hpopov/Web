package ua.kpi.iasa.web.lab3.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.UserConverter;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.UserService;


@Component
public class UserFacade {

	@Autowired
	private UserService userService;

	@Autowired
	private UserConverter userConverter;

	public UserData getUserByUsername(String username) {
    	UserModel userModel = userService.getUserByUsername(username);
    	return userConverter.modelToData(userModel);
	}
}
