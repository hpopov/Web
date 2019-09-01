package ua.kpi.iasa.web.lab3.dao;

import java.util.List;

import ua.kpi.iasa.web.lab3.model.SubjectModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

public interface SubjectDao {
	List<SubjectModel> getAllSubjects() throws DaoException;
	List<SubjectModel> getAllUserSubjects(UserModel user) throws DaoException;
}
