package ua.kpi.iasa.web.lab3.dao;

import java.util.List;
import java.util.Optional;

import ua.kpi.iasa.web.lab3.model.UserModel;

public interface UserDao {
	Optional<UserModel> findUserByUsername(String username) throws DaoException;
	Optional<UserModel> updateUser(UserModel user) throws DaoException;
	List<UserModel> getUserList();
	UserModel createUser(UserModel user);
	void deleteUser(UserModel user) throws DaoException;
}
