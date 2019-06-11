package ua.kpi.iasa.web.lab3.users.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.users.SimpleUser;

@Repository
public class PrimitiveUserDao implements UserDao {

	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public Optional<SimpleUser> findUserByUsername(String username) {
		SimpleUser user = null;
		if (username.equals("tordek")) {
			String password = encoder.encode("password");
			user = new SimpleUser("tordek", password);
			user.setId(0);
			user.setName("Hryhorii");
			user.setSurname("Popov");
		}
		return Optional.ofNullable(user);
	}

}
