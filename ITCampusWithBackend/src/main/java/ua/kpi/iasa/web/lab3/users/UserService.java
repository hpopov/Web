package ua.kpi.iasa.web.lab3.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.users.dao.UserDao;

@Service
public class UserService implements UserDetailsService{

	@Autowired()
//	@Qualifier("userDao")
    private UserDao userDao;

    @Override
    public SimpleUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
    
    public SimpleUser getUserByUsername(String username) {
    	return loadUserByUsername(username);
    }
    
}
