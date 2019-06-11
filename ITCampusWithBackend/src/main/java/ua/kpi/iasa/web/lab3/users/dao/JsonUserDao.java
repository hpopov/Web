package ua.kpi.iasa.web.lab3.users.dao;

import java.util.Optional;

//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.json.JsonReader;

import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.users.SimpleUser;

//@Repository
public class JsonUserDao implements UserDao{

	
	@Override
	public Optional<SimpleUser> findUserByUsername(String username) {
//		JsonReader json = Json.createReader(getClass().getResourceAsStream("user.json"));
//		JsonObject jsonObj = json.readObject().getValue(username).asJsonObject();
//		SimpleUser user = new SimpleUser(jsonObj.getString("login"), jsonObj.getString("password"));
//		user.setId(jsonObj.getInt("id"));
//		user.setName(jsonObj.getString("name"));
//		user.setSurname(jsonObj.getString("surname"));
//		return Optional.ofNullable(user);
		return null;
	}

}
