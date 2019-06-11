package ua.kpi.iasa.web.lab3.users.data;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.tokens.InvalidJwtAuthenticationException;
import ua.kpi.iasa.web.lab3.tokens.JwtTokenService;
import ua.kpi.iasa.web.lab3.users.SimpleUser;
import ua.kpi.iasa.web.lab3.users.UserService;


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
    	SimpleUser userModel = userService.getUserByUsername(jwtTokenService.getUsername(token));
		return makeUserDataFromUserModel(userModel);
	}

	private UserData makeUserDataFromUserModel(SimpleUser userModel) {
		UserData user = new UserData();
		user.setId(userModel.getId());
		user.setLogin(userModel.getUsername());
		user.setName(userModel.getName());
		user.setSurname(userModel.getSurname());
		return user;
	}

	public UserData getUserDataByUsername(String username) {
    	SimpleUser userModel = userService.getUserByUsername(username);
    	return makeUserDataFromUserModel(userModel);
	}
}
