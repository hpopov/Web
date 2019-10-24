package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.UserSkillPopulator;
import ua.kpi.iasa.web.lab3.model.UserSkillModel;

@Component
public class UserSkillConverter implements Converter<UserSkillModel, Entry<String, String>> {

    @Autowired
    private UserSkillPopulator userSkillPopulator;

    @Override
    public Entry<String, String> modelToData(UserSkillModel model) {
        return new ImmutablePair<>(model.getName(), model.getAssessment());
    }

    @Override
    public UserSkillModel dataToModel(@NonNull Entry<String, String> data) {
        return userSkillPopulator.populate(data, new UserSkillModel());
    }
}
