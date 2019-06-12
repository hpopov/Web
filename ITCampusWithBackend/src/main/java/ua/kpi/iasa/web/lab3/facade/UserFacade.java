package ua.kpi.iasa.web.lab3.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.UserService;
import ua.kpi.iasa.web.lab3.token.InvalidJwtAuthenticationException;
import ua.kpi.iasa.web.lab3.token.JwtTokenService;


@Component
public class UserFacade {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtTokenService jwtTokenService;

	public List<String> getUserAuthoritiesFromUsername(String username) {
		return userService.loadUserByUsername(username).getAuthorities().stream()
		.map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	}
	
	public UserData getUserDataFromToken(String token) throws InvalidJwtAuthenticationException {
    	jwtTokenService.validateToken(token);
    	UserModel userModel = userService.getUserByUsername(jwtTokenService.getUsername(token));
		return makeUserDataFromUserModel(userModel);
	}

	private UserData makeUserDataFromUserModel(UserModel userModel) {
		UserData user = new UserData();
		user.setId(userModel.getId());
		user.setLogin(userModel.getUsername());
		user.setName(userModel.getName());
		user.setSurname(userModel.getSurname());
		return user;
	}

	public UserData getUserDataByUsername(String username) {
    	UserModel userModel = userService.getUserByUsername(username);
    	return makeUserDataFromUserModel(userModel);
	}

	public Optional<UserData> getUserDataOptionalFromToken(String token) {
		if (jwtTokenService.isTokenValid(token)) {
	    	UserModel userModel = userService.getUserByUsername(jwtTokenService.getUsername(token));
			return Optional.of(makeUserDataFromUserModel(userModel));
		}
		return Optional.empty();
	}
}
