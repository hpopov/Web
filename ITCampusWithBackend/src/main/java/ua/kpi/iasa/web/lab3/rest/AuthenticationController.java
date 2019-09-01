package ua.kpi.iasa.web.lab3.rest;

import javax.servlet.http.HttpServletRequest;

import ua.kpi.iasa.web.lab3.data.AuthData;
import ua.kpi.iasa.web.lab3.data.CredentialsData;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.security.InvalidJwtAuthenticationException;

public interface AuthenticationController {

	AuthData authenticate(CredentialsData credentials);
	UserData getCurrentUser(HttpServletRequest request) throws InvalidJwtAuthenticationException;
}
