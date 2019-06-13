package ua.kpi.iasa.web.lab3.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel extends User {

	private static final long serialVersionUID = -400426412471385728L;
	private int id;
	private String name;
	private String surname;

	public UserModel(String username, String password, List<String> authorities) {
		super(username, password, authorities.stream()
				.map(authName -> new SimpleGrantedAuthority(authName))
				.collect(Collectors.toSet()));
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