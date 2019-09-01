package ua.kpi.iasa.web.lab3.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.SubjectDao;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.model.SubjectModel;
import ua.kpi.iasa.web.lab3.service.SubjectService;
import ua.kpi.iasa.web.lab3.service.exception.EntityNotFoundException;

@Service
public class DefaultSubjectService implements SubjectService {
	
	@Autowired
	SubjectDao subjectDao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List<SubjectModel> getSubjectsByUsername(String username) {
		try {
			final int userId = 
					userDao.findUserByUsername(username).orElseThrow(EntityNotFoundException::new).getId();
			return subjectDao.getAllSubjects().stream().filter(subject-> subject.getUserId() == userId)
					.collect(Collectors.toList());
		} catch (DaoException e) {
			throw new EntityNotFoundException(e);
		}
	}
}
