package ua.kpi.iasa.web.lab3.service;

import java.util.List;

import ua.kpi.iasa.web.lab3.model.SubjectModel;

public interface SubjectService {

	List<SubjectModel> getSubjectsByUsername(String username);

}