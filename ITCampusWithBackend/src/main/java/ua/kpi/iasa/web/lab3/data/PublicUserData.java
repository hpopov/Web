package ua.kpi.iasa.web.lab3.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicUserData {

	private String login;
	private String name;
	private String surname;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public PublicUserData(@JsonProperty String login, @JsonProperty String name,
			@JsonProperty String surname) {
		this.login = login;
		this.name = name;
		this.surname = surname;
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

}
