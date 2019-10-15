package ua.kpi.iasa.web.lab3.dao;

import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.dao.base.EnumRepository;
import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

@Repository
public interface AuthorityDao extends EnumRepository<AuthorityType, AuthorityModel> {
}
