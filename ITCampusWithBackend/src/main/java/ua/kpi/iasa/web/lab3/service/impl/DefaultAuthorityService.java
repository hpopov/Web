package ua.kpi.iasa.web.lab3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.dao.AuthorityDao;
import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;
import ua.kpi.iasa.web.lab3.service.AuthorityService;

@Service
public class DefaultAuthorityService extends LoggableComponent implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public AuthorityModel getOrCreateAuthorityForType(AuthorityType type) {
        return authorityDao.getOrCreateEnumModelByType(type);
    }
}
