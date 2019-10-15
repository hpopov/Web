package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.RegistrationData;
import ua.kpi.iasa.web.lab3.mapping.converter.DataToModelConverter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.RegistrationPopulator;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class RegistrationConverter implements DataToModelConverter<UserModel, RegistrationData> {

    @Autowired
    private RegistrationPopulator registrationPopulator;

    @Override
    public UserModel dataToModel(@NonNull RegistrationData data) {
        final UserModel model = new UserModel();
        model.setUserDetails(new UserDetailsModel());
        return registrationPopulator.populate(data, model);
    }
}
