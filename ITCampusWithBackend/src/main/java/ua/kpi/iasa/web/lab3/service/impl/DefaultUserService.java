package ua.kpi.iasa.web.lab3.service.impl;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.converter.PublicUserConverter;
import ua.kpi.iasa.web.lab3.converter.RegistrationConverter;
import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.data.RegistrationData;
import ua.kpi.iasa.web.lab3.model.AuthorityType;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.AuthorityService;
import ua.kpi.iasa.web.lab3.service.UserService;
import ua.kpi.iasa.web.lab3.service.exception.UnableToDeleteEntityException;

@Service
public class DefaultUserService extends LoggableComponent implements UserService{

	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private RegistrationConverter registrationConverter;
	@Autowired
	private PublicUserConverter publicUserConverter;
	@Autowired
    private UserDao userDao;
    
    @Override
	public UserModel getUserByUsername(String username) {
    	try {
			return userDao.findUserByUsername(username)
			    .orElseThrow(() -> 
			    	new UsernameNotFoundException("Username: " + username + " not found"));
		} catch (DaoException e) {
			throw new UsernameNotFoundException("Username: " + username + " not found", e);
		}
    }

	@Override
	public List<UserModel> getAllUsers() {
		return userDao.getUserList();
	}

	@Override
	public UserModel createRegularUser(RegistrationData registrationData) {
		final UserModel user = registrationConverter.dataToModel(registrationData);
		final Collection<AuthorityType> regularUserAuthorities = EnumSet.of(AuthorityType.ROLE_USER);
		final UserDetailsModel userDetails = user.getUserDetails();
		userDetails.setAuthorities(regularUserAuthorities.stream()
				.map(authorityService::makeAuthorityForType).collect(Collectors.toSet()));
		return userDao.createUser(user);
	}

	@Override
	public Optional<UserModel> updateUser(PublicUserData publicUserData) {
		final UserModel user = getUserByUsername(publicUserData.getLogin());
		publicUserConverter.populateDataToModel(user, publicUserData);
    	try {
			return userDao.updateUser(user);
		} catch (DaoException e) {
			log.error("Unable to update the user", e);
			return Optional.ofNullable(null);
		}
	}

	@Override
	public void deleteUser(String username) {
		final UserModel user = getUserByUsername(username);
		try {
			userDao.deleteUser(user);
		} catch (DaoException e) {
			throw new UnableToDeleteEntityException("Error occured trying to delete user "+username, e);
		}
	}
    
}
