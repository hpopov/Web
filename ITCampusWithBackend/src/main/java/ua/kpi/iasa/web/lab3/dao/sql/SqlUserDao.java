package ua.kpi.iasa.web.lab3.dao.sql;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Primary
@Repository
public class SqlUserDao extends AbstractSqlDao implements UserDao {

	@Override
	public Optional<UserModel> findUserByUsername(String username) throws DaoException {
		TypedQuery<UserModel> jpql = entityManager.createQuery("SELECT u FROM UserModel u WHERE u.username = :username",
				UserModel.class);
		jpql.setParameter("username", username);
		return Optional.ofNullable(jpql.getSingleResult());
	}

	@Transactional
	@Override
	public Optional<UserModel> updateUser(UserModel user) throws DaoException {
		return Optional.ofNullable(entityManager.merge(user));
	}

	@Transactional
	@Override
	public List<UserModel> getUserList() {
		return entityManager.createQuery("SELECT u FROM UserModel u", UserModel.class).getResultList();
	}

	@Transactional
	@Override
	public UserModel createUser(UserModel user) {
//		entityManager.merge(user.getUserDetails());
//		entityManager.persist(user.getUserDetails());
		entityManager.persist(user);
//		entityManager.persist(user.getPersonalInfo());
//		entityManager.merge(user);
		return user;
	}

	@Transactional
	@Override
	public void deleteUser(UserModel user) {
		entityManager.remove(user);
	}

}
