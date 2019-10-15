package ua.kpi.iasa.web.lab3.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.model.SubjectModel;

@Repository
public interface SubjectDao extends CrudRepository<SubjectModel, Integer> {
//	List<SubjectModel> findAll() throws DaoException;
    List<SubjectModel> findAllByUserId(Integer userId);
}
