package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.UserLanguagePopulator;
import ua.kpi.iasa.web.lab3.model.UserLanguageModel;

@Component
public class UserLanguageConverter implements Converter<UserLanguageModel, String[]> {

    @Autowired
    private UserLanguagePopulator userLanguagePopulator;
    
    @Override
    public String[] modelToData(@NonNull UserLanguageModel model) {
        return new String[] { model.getLanguage(), model.getAssessment() };
    }

    @Override
    public UserLanguageModel dataToModel(@NonNull String[] data) {
        return userLanguagePopulator.populate(data, new UserLanguageModel());
    }
}
