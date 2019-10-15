package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.PublicUserPopulator;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class PublicUserConverter extends LoggableComponent implements Converter<UserModel, PublicUserData> {

    @Autowired
    private PublicUserPopulator publicUserPopulator;
    
    @Override
    public PublicUserData modelToData(UserModel model) {
        String surname = model.getSurname();
        String name = model.getName();
        String login = model.getUsername();
        return new PublicUserData(login, name, surname);
    }

    @Override
    public UserModel dataToModel(@NonNull PublicUserData data) {
        final UserModel model = new UserModel();
        model.setUserDetails(new UserDetailsModel());
        return publicUserPopulator.populate(data, model);
    }
}
