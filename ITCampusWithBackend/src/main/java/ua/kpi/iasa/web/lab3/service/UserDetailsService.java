package ua.kpi.iasa.web.lab3.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ua.kpi.iasa.web.lab3.model.UserDetailsModel;

public interface UserDetailsService
	extends org.springframework.security.core.userdetails.UserDetailsService {
	
	public UserDetailsModel getUserDetailsByUsername(String username)
			throws UsernameNotFoundException;
}
