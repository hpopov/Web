package ua.kpi.iasa.web.lab3.security.auth;

import java.security.Principal;

public class SimplePrincipal implements Principal {

	private final String username;
	
	public SimplePrincipal(String username) {
		this.username = username;
	}

	@Override
	public String getName() {
		return username;
	}

}
