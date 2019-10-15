package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.SubjectData;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.SubjectModel;
import ua.kpi.iasa.web.lab3.model.TestType;

@Component
public class SubjectPopulator implements Populator<SubjectModel, SubjectData> {

    @Override
    public SubjectModel populate(@NonNull SubjectData data, @NonNull SubjectModel model) {
        model.setAcademicHours(data.getAcademicHours());
        model.setMark(data.getMark());
        model.setName(data.getName());
        model.setTeacher(data.getTeacher());
        model.setTestType(convertTestType(data.getTestType()));
        model.setSubjectId(data.getSubjectId());
        return model;
    }

    private TestType convertTestType(String test) {
        return TestType.valueOf(test.toUpperCase());
    }
}
