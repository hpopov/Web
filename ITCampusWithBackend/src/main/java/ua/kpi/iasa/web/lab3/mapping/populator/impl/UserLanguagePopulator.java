package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.converter.ConverterException;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.UserLanguageModel;

@Component
public class UserLanguagePopulator implements Populator<UserLanguageModel, String[]> {

    @Override
    public UserLanguageModel populate(@NonNull String[] data, @NonNull UserLanguageModel model) {
        if (data.length < 2) {
            throw new ConverterException(data, UserLanguageModel.class);
        }
        model.setLanguage(data[0]);
        model.setAssessment(data[1]);
        return model;
    }
}
