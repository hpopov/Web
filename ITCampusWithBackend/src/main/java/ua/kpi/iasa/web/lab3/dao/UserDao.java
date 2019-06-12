package ua.kpi.iasa.web.lab3.dao;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import ua.kpi.iasa.web.lab3.model.UserModel;

public interface UserDao {
	Optional<UserModel> findUserByUsername(String username);
}
