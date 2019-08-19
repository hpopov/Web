package ua.kpi.iasa.web.lab3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.converter.UserDetailsConverter;
import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.UserDetailsDao;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.service.UserDetailsService;

@Service
public class DefaultUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private UserDetailsConverter userDetailsConverter;
	
	@Override
	public UserDetailsModel getUserDetailsByUsername(String username)
			throws UsernameNotFoundException {
		try {
			return userDetailsDao.getUserDetailsByUsername(username).orElseThrow(() -> 
				new UsernameNotFoundException("Username: " + username + " not found"));
		} catch (DaoException e) {
			throw new UsernameNotFoundException("Username: " + username + " not found", e);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userDetailsConverter.modelToData(getUserDetailsByUsername(username));
	}
}
