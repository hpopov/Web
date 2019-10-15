package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

@Component
public class AuthorityPopulator implements Populator<AuthorityModel, AuthorityType> {

    @Override
    public AuthorityModel populate(@NonNull AuthorityType data, @NonNull AuthorityModel model) {
        model.setValue(data);
        return model;
    }
}
