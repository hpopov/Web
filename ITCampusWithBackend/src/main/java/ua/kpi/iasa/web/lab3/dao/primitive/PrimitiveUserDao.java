package ua.kpi.iasa.web.lab3.dao.primitive;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.exception.UnableToMakeEntityException;

@Repository
//@Qualifier("userDao")
public class PrimitiveUserDao implements UserDao {

//	@Autowired
//	private PasswordEncoder encoder;
	
	@Override
	public Optional<UserModel> findUserByUsername(String username) {
		UserModel user = null;
		if (username.equals("tordek")) {
//			String password = encoder.encode("password");
			user = new UserModel(/* "tordek", password, Arrays.asList("USER") */);
			user.setId(0);
			user.setName("Hryhorii");
			user.setSurname("Popov");
		}
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<UserModel> updateUser(UserModel user) throws DaoException {
		return Optional.ofNullable(null);
	}

	@Override
	public List<UserModel> getUserList() {
		return Collections.emptyList();
	}

	@Override
	public UserModel createUser(UserModel user) {
		throw new UnableToMakeEntityException("Unable to create user using PrimitiveUserDao");
	}

	@Override
	public void deleteUser(UserModel user) {
		// TODO Auto-generated method stub
		
	}

}
