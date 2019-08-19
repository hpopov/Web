package ua.kpi.iasa.web.lab3.security;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

	public InvalidJwtAuthenticationException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 2791243484744087950L;

}
