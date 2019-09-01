package ua.kpi.iasa.web.lab3.dao.sql;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.iasa.web.lab3.converter.AuthorityConverter;
import ua.kpi.iasa.web.lab3.dao.AuthorityDao;
import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

@Primary
@Repository
public class SqlAuthorityDao extends AbstractSqlDao implements AuthorityDao {

	@Autowired
	private AuthorityConverter authorityConverter;
	@Transactional
	@Override
	public List<AuthorityModel> getAllAuthorities() throws DaoException {
		return entityManager.createQuery("SELECT a FROM AuthorityModel a", AuthorityModel.class)
				.getResultList();
	}

	@Transactional
	@Override
	public AuthorityModel getOrCreateAuthorityByType(AuthorityType authorityType) throws DaoException {
		try {
		return entityManager.createQuery("SELECT a FROM AuthorityModel a WHERE a.authorityRole = :role",
				AuthorityModel.class)
				.setParameter("role", authorityType)
				.getSingleResult();
		} catch (NoResultException e) {
			log.debug("authorityModel with type " + authorityType + " was not found. Creating a new one...");
			final AuthorityModel authorityModel = authorityConverter.dataToModel(authorityType);
			entityManager.persist(authorityModel);
			return authorityModel;
		}
	}

}
