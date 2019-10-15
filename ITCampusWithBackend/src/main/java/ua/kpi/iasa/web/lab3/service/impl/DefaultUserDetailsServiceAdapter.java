package ua.kpi.iasa.web.lab3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.dao.UserDetailsDao;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.UserDetailsConverter;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.service.UserDetailsServiceAdapter;

@Service
public class DefaultUserDetailsServiceAdapter implements UserDetailsServiceAdapter {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Autowired
    private UserDetailsConverter userDetailsConverter;

    private UserDetailsModel getUserDetailsByUsername(String username) throws UsernameNotFoundException {
        return userDetailsDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsConverter.modelToData(getUserDetailsByUsername(username));
    }

}
