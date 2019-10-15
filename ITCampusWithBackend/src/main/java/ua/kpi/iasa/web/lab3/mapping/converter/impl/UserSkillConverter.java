package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.UserSkillPopulator;
import ua.kpi.iasa.web.lab3.model.UserSkillModel;

@Component
public class UserSkillConverter implements Converter<UserSkillModel, String[]> {

    @Autowired
    private UserSkillPopulator userSkillPopulator;

    @Override
    public String[] modelToData(UserSkillModel model) {
        return new String[] { model.getName(), model.getAssessment() };
    }

    @Override
    public UserSkillModel dataToModel(String @NonNull [] data) {
        return userSkillPopulator.populate(data, new UserSkillModel());
    }
}
