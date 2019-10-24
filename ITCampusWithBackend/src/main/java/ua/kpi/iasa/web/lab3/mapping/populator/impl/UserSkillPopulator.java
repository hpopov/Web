package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.UserSkillModel;

@Component
public class UserSkillPopulator implements Populator<UserSkillModel, Entry<String, String>> {

    @Override
    public UserSkillModel populate(@NonNull Entry<String, String> data, @NonNull UserSkillModel model) {
        model.setName(data.getKey());
        model.setAssessment(data.getValue());
        return model;
    }
}
