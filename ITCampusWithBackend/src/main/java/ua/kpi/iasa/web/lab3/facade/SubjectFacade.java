package ua.kpi.iasa.web.lab3.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.data.SubjectData;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.SubjectModel;
import ua.kpi.iasa.web.lab3.service.SubjectService;

@Service
public class SubjectFacade {
	
	@Autowired
	SubjectService subjectService;
	
	public List<SubjectData> getSubjectsByUser(UserData user) {
		final SubjectConverter converter = new SubjectConverter();
		return subjectService.getSubjectsByUserId(user.getId()).parallelStream()
			.map(converter::convert).collect(Collectors.toList());
	}

}

class SubjectConverter {
	private int currentNumber = 1;
	
	public SubjectData convert(SubjectModel model) {
		SubjectData data = new SubjectData();
		data.setAcademicHours(model.getAcademicHours());
		data.setMark(model.getMark());
		data.setName(model.getName());
		data.setNum(currentNumber++);
		data.setTeacher(model.getTeacher());
		data.setTestType(model.getTestType().toString());
		return data;
	}
}
