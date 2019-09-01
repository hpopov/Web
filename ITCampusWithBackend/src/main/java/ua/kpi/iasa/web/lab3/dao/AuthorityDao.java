package ua.kpi.iasa.web.lab3.dao;

import java.util.List;

import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

public interface AuthorityDao {
	public List<AuthorityModel> getAllAuthorities() throws DaoException;
	public AuthorityModel getOrCreateAuthorityByType(AuthorityType authorityType) throws DaoException;
}
