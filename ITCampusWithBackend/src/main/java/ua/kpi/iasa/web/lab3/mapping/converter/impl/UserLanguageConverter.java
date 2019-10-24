package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.UserLanguagePopulator;
import ua.kpi.iasa.web.lab3.model.UserLanguageModel;

@Component
public class UserLanguageConverter implements Converter<UserLanguageModel, Map.Entry<String,String>> {

    @Autowired
    private UserLanguagePopulator userLanguagePopulator;
    
    @Override
    public Entry<String, String> modelToData(@NonNull UserLanguageModel model) {
        return new ImmutablePair<>(model.getLanguage(), model.getAssessment());
    }

    @Override
    public UserLanguageModel dataToModel(@NonNull Entry<String, String> data) {
        return userLanguagePopulator.populate(data, new UserLanguageModel());
    }
}
