package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.AuthorityPopulator;
import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

@Component
public class AuthorityConverter implements Converter<AuthorityModel, AuthorityType> {

    @Autowired
    private AuthorityPopulator authorityPopulator;

    @Override
    public AuthorityType modelToData(@NonNull AuthorityModel model) {
        return model.getValue();
    }

    @Override
    public AuthorityModel dataToModel(@NonNull AuthorityType data) {
        return authorityPopulator.populate(data, new AuthorityModel());
    }
}
