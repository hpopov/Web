package ua.kpi.iasa.web.lab3.service;

import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;
import ua.kpi.iasa.web.lab3.service.exception.UnableToMakeEntityException;

public interface AuthorityService {
	AuthorityModel makeAuthorityForType(AuthorityType type) throws UnableToMakeEntityException;
}
