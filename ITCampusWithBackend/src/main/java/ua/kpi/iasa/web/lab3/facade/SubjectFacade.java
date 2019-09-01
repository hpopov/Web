package ua.kpi.iasa.web.lab3.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.converter.Converter;
import ua.kpi.iasa.web.lab3.converter.SubjectConverter;
import ua.kpi.iasa.web.lab3.data.SubjectData;
import ua.kpi.iasa.web.lab3.service.SubjectService;

@Service
public class SubjectFacade {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SubjectConverter subjectConverter;
	
	public List<SubjectData> getSubjectsByUsername(String username) {
		return Converter.mapCollectionToList(subjectConverter::modelToData,
				subjectService.getSubjectsByUsername(username));
	}

}
