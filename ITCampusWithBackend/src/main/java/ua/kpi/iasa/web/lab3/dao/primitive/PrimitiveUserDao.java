package ua.kpi.iasa.web.lab3.dao.primitive;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Repository
//@Qualifier("userDao")
public class PrimitiveUserDao implements UserDao {

	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public Optional<UserModel> findUserByUsername(String username) {
		UserModel user = null;
		if (username.equals("tordek")) {
			String password = encoder.encode("password");
			user = new UserModel(/* "tordek", password, Arrays.asList("USER") */);
			user.setId(0);
			user.setName("Hryhorii");
			user.setSurname("Popov");
		}
		return Optional.ofNullable(user);
	}

	@Override
	public void updateUser(UserData user) throws DaoException {
		// TODO Auto-generated method stub
		
	}

}
