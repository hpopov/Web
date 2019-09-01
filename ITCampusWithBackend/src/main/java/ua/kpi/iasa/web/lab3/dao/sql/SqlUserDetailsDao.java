package ua.kpi.iasa.web.lab3.dao.sql;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.UserDetailsDao;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;

@Primary
@Repository
public class SqlUserDetailsDao extends AbstractSqlDao implements UserDetailsDao {

	@Transactional
	@Override
	public Optional<UserDetailsModel> getUserDetailsByUsername(String username) throws DaoException {
		return Optional.ofNullable(entityManager.find(UserDetailsModel.class, username));
	}

}
