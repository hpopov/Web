package ua.kpi.iasa.web.lab3.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.UserService;

@Service
public class DefaultUserService implements UserService{

	private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);
	
	@Autowired()
	@Qualifier("userDao")
    private UserDao userDao;
    
    @Override
	public UserModel getUserByUsername(String username) {
    	try {
			return userDao.findUserByUsername(username)
			    .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
		} catch (DaoException e) {
			throw new UsernameNotFoundException("Username: " + username + " not found", e);
		}
    }
    
    @Override
	public boolean updateUserById(UserData user) {
    	try {
			userDao.updateUser(user);
		} catch (DaoException e) {
			LOG.error("Unable to update the user", e);
			return false;
		}
    	LOG.debug("User was updated successfully");
    	return true;
    }
    
}
