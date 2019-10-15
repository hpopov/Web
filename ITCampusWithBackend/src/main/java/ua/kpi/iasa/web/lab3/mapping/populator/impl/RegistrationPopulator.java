package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.RegistrationData;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class RegistrationPopulator implements Populator<UserModel, RegistrationData> {

    @Autowired
    private PublicUserPopulator publicUserPopulator;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel populate(@NonNull RegistrationData data, @NonNull UserModel model) {
        model = publicUserPopulator.populate(data.getUser(), model);
        model.getUserDetails().setPassword(passwordEncoder.encode(data.getPassword()));
        return model;
    }
}
