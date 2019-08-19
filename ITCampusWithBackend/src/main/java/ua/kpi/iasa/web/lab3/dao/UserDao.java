package ua.kpi.iasa.web.lab3.dao;

import java.util.Optional;

import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.UserModel;

public interface UserDao {
	Optional<UserModel> findUserByUsername(String username) throws DaoException;
	void updateUser(UserData user) throws DaoException;
}
