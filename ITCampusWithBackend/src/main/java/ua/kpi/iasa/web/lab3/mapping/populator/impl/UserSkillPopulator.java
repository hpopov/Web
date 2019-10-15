package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.converter.ConverterException;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.UserSkillModel;

@Component
public class UserSkillPopulator implements Populator<UserSkillModel, String[]> {

    @Override
    public UserSkillModel populate(String @NonNull [] data, @NonNull UserSkillModel model) {
        if (data.length < 2) {
            throw new ConverterException(data, UserSkillModel.class);
        }
        model.setName(data[0]);
        model.setAssessment(data[1]);
        return model;
    }
}
