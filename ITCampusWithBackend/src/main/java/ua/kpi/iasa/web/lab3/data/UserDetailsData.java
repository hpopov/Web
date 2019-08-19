package ua.kpi.iasa.web.lab3.data;

import java.util.EnumSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import ua.kpi.iasa.web.lab3.model.AuthorityType;

@SuppressWarnings("serial")
public class UserDetailsData implements UserDetails, CredentialsContainer {

	private String username;
	
	private String password;
	
	private EnumSet<AuthorityType> authorities;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public UserDetailsData(@JsonProperty("username")String username,
			@JsonProperty("password")String password,
			@JsonProperty("roles")EnumSet<AuthorityType> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public EnumSet<AuthorityType> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(EnumSet<AuthorityType> authorities) {
		this.authorities = authorities;
	}


	@Override
	public void eraseCredentials() {
		this.password = null;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}

}
