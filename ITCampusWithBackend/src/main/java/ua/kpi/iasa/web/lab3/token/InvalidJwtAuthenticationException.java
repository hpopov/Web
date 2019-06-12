package ua.kpi.iasa.web.lab3.token;

public class InvalidJwtAuthenticationException extends RuntimeException {

	public InvalidJwtAuthenticationException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 2791243484744087950L;

}
