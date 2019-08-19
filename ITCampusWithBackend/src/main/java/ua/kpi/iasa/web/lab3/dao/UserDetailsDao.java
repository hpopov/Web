package ua.kpi.iasa.web.lab3.dao;

import java.util.Optional;

import ua.kpi.iasa.web.lab3.model.UserDetailsModel;

public interface UserDetailsDao {
	public Optional<UserDetailsModel> getUserDetailsByUsername(String username)
		throws DaoException;
}
