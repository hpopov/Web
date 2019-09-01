package ua.kpi.iasa.web.lab3.converter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ua.kpi.iasa.web.lab3.data.SubjectData;
import ua.kpi.iasa.web.lab3.model.SubjectModel;
import ua.kpi.iasa.web.lab3.model.TestType;

@Component
public class SubjectConverter implements Converter<SubjectModel, SubjectData> {

	@Override
	public SubjectData modelToData(SubjectModel model) {
		SubjectData data = new SubjectData();
		data.setAcademicHours(model.getAcademicHours());
		data.setMark(model.getMark());
		data.setName(model.getName());
		data.setTeacher(model.getTeacher());
		data.setTestType(convertTestType(model.getTestType()));
		data.setSubjectId(model.getSubjectId());
		return data;
	}
	
	private String convertTestType(TestType test) {
		return StringUtils.capitalize(test.toString().toLowerCase());
	}
	
	private TestType convertTestType(String test) {
		return TestType.valueOf(test.toUpperCase());
	}

	@Override
	public SubjectModel populateDataToModel(SubjectModel model, SubjectData data) {
		if (model == null) {
			model = new SubjectModel();
		}
		model.setAcademicHours(data.getAcademicHours());
		model.setMark(data.getMark());
		model.setName(data.getName());
		model.setTeacher(data.getTeacher());
		model.setTestType(convertTestType(data.getTestType()));
		model.setSubjectId(data.getSubjectId());
		return model;
	}

}
