package ua.kpi.iasa.web.lab3.facade;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.converter.AuthorityConverter;
import ua.kpi.iasa.web.lab3.converter.UserConverter;
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
	private UserConverter userConverter;
	@Autowired
	private AuthorityConverter authorityConverter;

	public Set<String> getUserAuthoritiesFromUsername(String username) {
		return userService.getUserByUsername(username).getUserDetails().getAuthorities()
				.stream().map(authorityConverter::modelToData).map(GrantedAuthority::getAuthority)
				.collect(Collectors.toSet());
	}

//	private UserData makeUserDataFromUserModel(UserModel userModel) {
//		UserData user = new UserData();
//		user.setId(userModel.getId());
//		user.setLogin(userModel.getUserDetails().getUsername());
//		user.setName(userModel.getName());
//		user.setSurname(userModel.getSurname());
//		user.setAuthorities(userModel.getUserDetails().getAuthorities().stream()
//				.map(AuthorityModel::getAuthorityRole).map(GrantedAuthority::getAuthority)
//				.collect(Collectors.toList()));
//		return user;
//	}

	public UserData getUserByUsername(String username) {
    	UserModel userModel = userService.getUserByUsername(username);
    	return userConverter.modelToData(userModel);
	}

//	public Optional<UserData> getUserDataOptionalFromToken(String token) {
//		if (jwtTokenService.isTokenValid(token)) {
//	    	UserModel userModel = userService.getUserByUsername(jwtTokenService.parseUsername(token));
//			return Optional.of(makeUserDataFromUserModel(userModel));
//		}
//		return Optional.empty();
//	}
}
