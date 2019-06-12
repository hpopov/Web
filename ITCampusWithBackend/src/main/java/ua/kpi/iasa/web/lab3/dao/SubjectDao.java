package ua.kpi.iasa.web.lab3.dao;

import java.util.List;

import ua.kpi.iasa.web.lab3.model.SubjectModel;

public interface SubjectDao {
	List<SubjectModel> getAllSubjects() throws DaoException ;
}
