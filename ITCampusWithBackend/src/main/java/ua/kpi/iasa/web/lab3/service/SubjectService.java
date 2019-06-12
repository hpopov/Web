package ua.kpi.iasa.web.lab3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.SubjectDao;
import ua.kpi.iasa.web.lab3.model.SubjectModel;

@Service
public class SubjectService {
	
	@Autowired
	@Qualifier("subjectDao")
	SubjectDao subjectDao;
	
	public List<SubjectModel> getSubjectsByUserId(int userId) {
		try {
			return subjectDao.getAllSubjects().stream().filter(subject-> subject.getUserId() == userId)
					.collect(Collectors.toList());
		} catch (DaoException e) {
			throw new EntityNotFoundException(e);
		}
	}
}
