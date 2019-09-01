package ua.kpi.iasa.web.lab3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.dao.AuthorityDao;
import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;
import ua.kpi.iasa.web.lab3.service.AuthorityService;
import ua.kpi.iasa.web.lab3.service.exception.UnableToMakeEntityException;

@Service
public class DefaultAuthorityService extends LoggableComponent implements AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;
	
	@Override
	public AuthorityModel makeAuthorityForType(AuthorityType type) throws UnableToMakeEntityException {
		try {
			final AuthorityModel authority = authorityDao.getOrCreateAuthorityByType(type);
			return authority;
		} catch (DaoException e) {
			throw new UnableToMakeEntityException("Unable to make authority model", e);
		}
	}

}
