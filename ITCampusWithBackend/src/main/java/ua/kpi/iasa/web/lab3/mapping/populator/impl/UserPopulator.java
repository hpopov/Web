package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.AuthorityConverter;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class UserPopulator implements Populator<UserModel, UserData> {

    @Autowired
    private AuthorityConverter authorityConverter;
    
    @Override
    public UserModel populate(@NonNull UserData data, @NonNull UserModel model) {
        model.setName(data.getName());
        model.setSurname(data.getSurname());
        model.setUsername(data.getLogin());
        UserDetailsModel userDetailsModel = model.getUserDetails();
        userDetailsModel.setUsername(data.getLogin());
        userDetailsModel.setAuthorities(Converter.mapToSet(authorityConverter::dataToModel,
                data.getAuthorities()));
        model.setUserDetails(userDetailsModel);
        return model;
    }
}
