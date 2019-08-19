package ua.kpi.iasa.web.lab3.facade;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.security.InvalidJwtAuthenticationException;
import ua.kpi.iasa.web.lab3.security.JwtTokenService;
import ua.kpi.iasa.web.lab3.service.UserService;


@Component
public class UserFacade {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtTokenService jwtTokenService;

	public Set<String> getUserAuthoritiesFromUsername(String username) {
		return userService.getUserByUsername(username).getUserDetails().getAuthorities()
				.stream().map(AuthorityModel::getAuthorityRole)
		.map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
	}
	
	public UserData getUserDataFromToken(String token) throws InvalidJwtAuthenticationException {
    	jwtTokenService.validateToken(token);
    	UserModel userModel = userService.getUserByUsername(jwtTokenService.parseUsername(token));
		return makeUserDataFromUserModel(userModel);
	}

	private UserData makeUserDataFromUserModel(UserModel userModel) {
		UserData user = new UserData();
		user.setId(userModel.getId());
		user.setLogin(userModel.getUserDetails().getUsername());
		user.setName(userModel.getName());
		user.setSurname(userModel.getSurname());
		user.setAuthorities(userModel.getUserDetails().getAuthorities().stream()
				.map(AuthorityModel::getAuthorityRole).map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList()));
		return user;
	}

	public UserData getUserDataByUsername(String username) {
    	UserModel userModel = userService.getUserByUsername(username);
    	return makeUserDataFromUserModel(userModel);
	}

//	public Optional<UserData> getUserDataOptionalFromToken(String token) {
//		if (jwtTokenService.isTokenValid(token)) {
//	    	UserModel userModel = userService.getUserByUsername(jwtTokenService.parseUsername(token));
//			return Optional.of(makeUserDataFromUserModel(userModel));
//		}
//		return Optional.empty();
//	}
}
