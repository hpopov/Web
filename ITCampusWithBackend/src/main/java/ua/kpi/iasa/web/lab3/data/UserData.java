package ua.kpi.iasa.web.lab3.data;

import java.util.EnumSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ua.kpi.iasa.web.lab3.model.AuthorityType;

public class UserData {

	private String login;
	private String name;
	private String surname;
	private EnumSet<AuthorityType> authorities;
	
	public UserData() {}
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public UserData(@JsonProperty String login,
			@JsonProperty String name, @JsonProperty String surname,
			@JsonProperty EnumSet<AuthorityType> authorities) {
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.authorities = authorities;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public EnumSet<AuthorityType> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(EnumSet<AuthorityType> authorities) {
		this.authorities = authorities;
	}
	
}
