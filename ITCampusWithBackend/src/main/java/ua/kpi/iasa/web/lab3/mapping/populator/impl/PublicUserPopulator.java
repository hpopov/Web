package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class PublicUserPopulator extends LoggableComponent implements Populator<UserModel, PublicUserData> {

    @Override
    public UserModel populate(@NonNull PublicUserData data, @NonNull UserModel model) {
        log.info("Populating PublicUserData to UserModel...");
        model.setName(data.getName());
        model.setSurname(data.getSurname());
        model.setUsername(data.getLogin());
        model.getUserDetails().setUsername(data.getLogin());
        return model;
    }

}
