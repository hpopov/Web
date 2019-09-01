package ua.kpi.iasa.web.lab3.dao.sql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.SubjectDao;
import ua.kpi.iasa.web.lab3.model.SubjectModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Primary
@Repository
public class SqlSubjectDao extends AbstractSqlDao implements SubjectDao {

	@Override
	public List<SubjectModel> getAllSubjects() throws DaoException {
		TypedQuery<SubjectModel> jpql =
				entityManager.createQuery("SELECT s FROM SubjectModel s", SubjectModel.class);
		return jpql.getResultList();
	}

	@Override
	public List<SubjectModel> getAllUserSubjects(UserModel user) throws DaoException {
		return user.getSubjects();
	}

}
