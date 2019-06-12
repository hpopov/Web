package ua.kpi.iasa.web.lab3.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CredentialsData {

	private String username;
	private String password;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public CredentialsData(@JsonProperty String username,
			@JsonProperty String password) {
		this.username = username;
		this.password = password;
	}
	
//	public CredentialsData() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
}
