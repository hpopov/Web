package ua.kpi.iasa.web.lab3.users.dao;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import ua.kpi.iasa.web.lab3.users.SimpleUser;

public interface UserDao {
	Optional<SimpleUser> findUserByUsername(String username);
}
