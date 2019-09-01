package ua.kpi.iasa.web.lab3.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationData {
	private PublicUserData user;
	private String password;

	@JsonCreator(mode = Mode.PROPERTIES)
	public RegistrationData(@JsonProperty PublicUserData user, @JsonProperty String password) {
		this.setUser(user);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PublicUserData getUser() {
		return user;
	}

	public void setUser(PublicUserData user) {
		this.user = user;
	}

}
