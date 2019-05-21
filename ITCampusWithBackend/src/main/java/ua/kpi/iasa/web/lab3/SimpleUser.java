package ua.kpi.iasa.web.lab3;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -400426412471385728L;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public SimpleUser(@JsonProperty String username,
			@JsonProperty String password) {
		super(username, password,
				new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("USER"))));
	}

}
