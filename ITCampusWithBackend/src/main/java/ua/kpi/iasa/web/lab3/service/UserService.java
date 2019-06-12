package ua.kpi.iasa.web.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Service
public class UserService implements UserDetailsService{

	@Autowired()
	@Qualifier("userDao")
    private UserDao userDao;

    @Override
    public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
    
    public UserModel getUserByUsername(String username) {
    	return loadUserByUsername(username);
    }
    
}
