package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.SubjectData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.SubjectPopulator;
import ua.kpi.iasa.web.lab3.model.SubjectModel;
import ua.kpi.iasa.web.lab3.model.TestType;

@Component
public class SubjectConverter implements Converter<SubjectModel, SubjectData> {

    @Autowired
    private SubjectPopulator subjectPopulator;
    
	@Override
	public SubjectData modelToData(@NonNull SubjectModel model) {
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

    @Override
    public SubjectModel dataToModel(@NonNull SubjectData data) {
        return subjectPopulator.populate(data, new SubjectModel());
    }
	

}
