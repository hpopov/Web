package ua.kpi.iasa.web.lab3.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {

	private int id;
	private String login;
	private String name;
	private String surname;
	private List<String> authorities;
	
	public UserData() {}
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public UserData(@JsonProperty int id, @JsonProperty String login,
			@JsonProperty String name, @JsonProperty String surname,
			@JsonProperty List<String> authorities) {
		this.id = id;
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.authorities = authorities;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
}
