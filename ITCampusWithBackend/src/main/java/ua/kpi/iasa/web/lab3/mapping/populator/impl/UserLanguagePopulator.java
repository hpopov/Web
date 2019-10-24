package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.UserLanguageModel;

@Component
public class UserLanguagePopulator implements Populator<UserLanguageModel, Map.Entry<String,String>> {

    @Override
    public UserLanguageModel populate(@NonNull Map.Entry<String,String> data, @NonNull UserLanguageModel model) {
        model.setLanguage(data.getKey());
        model.setAssessment(data.getValue());
        return model;
    }
}
