package ua.kpi.iasa.web.lab3.service;

import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

public interface AuthorityService {

    AuthorityModel getOrCreateAuthorityForType(AuthorityType type);
}
