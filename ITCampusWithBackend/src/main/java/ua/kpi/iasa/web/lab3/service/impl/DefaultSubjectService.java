package ua.kpi.iasa.web.lab3.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.dao.SubjectDao;
import ua.kpi.iasa.web.lab3.dao.UserDao;
import ua.kpi.iasa.web.lab3.model.SubjectModel;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.SubjectService;

@Service
public class DefaultSubjectService implements SubjectService {

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    UserDao userDao;

    @Override
    public List<SubjectModel> getSubjectsByUsername(String username) {
        final UserModel user = userDao.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return subjectDao.findAllByUserId(user.getId());
    }
}
