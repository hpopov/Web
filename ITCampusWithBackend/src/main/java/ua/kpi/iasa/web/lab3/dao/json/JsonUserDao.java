package ua.kpi.iasa.web.lab3.dao.json;

import java.util.Optional;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Repository
public class JsonUserDao implements UserDao{

	
	@Override
	public Optional<UserModel> findUserByUsername(String username) {
		JsonReader json = Json.createReader(getClass().getResourceAsStream("user.json"));
		JsonObject jsonObj = json.readObject().getValue(username).asJsonObject();
		UserModel user = new UserModel(jsonObj.getString("login"), jsonObj.getString("password")
				,null);
		user.setId(jsonObj.getInt("id"));
		user.setName(jsonObj.getString("name"));
		user.setSurname(jsonObj.getString("surname"));
		return Optional.ofNullable(user);
	}

}
