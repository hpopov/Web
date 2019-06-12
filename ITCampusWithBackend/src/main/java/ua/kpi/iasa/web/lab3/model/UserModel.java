package ua.kpi.iasa.web.lab3.model;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel extends User {

	private static final long serialVersionUID = -400426412471385728L;
	private int id;
	private String name;
	private String surname;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public UserModel(@JsonProperty String username,
			@JsonProperty String password) {
		super(username, password,
				new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("USER"))));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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