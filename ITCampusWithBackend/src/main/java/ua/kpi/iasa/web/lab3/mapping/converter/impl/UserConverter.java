package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.UserPopulator;
import ua.kpi.iasa.web.lab3.model.AuthorityType;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class UserConverter implements Converter<UserModel, UserData>{
	
	@Autowired
	private AuthorityConverter authorityConverter;
	@Autowired
	private UserPopulator userPopulator;
	
	@Override
	public UserData modelToData(@NonNull UserModel model) {
		final EnumSet<AuthorityType> authorities = Converter.mapToEnumSet(authorityConverter::modelToData,
				model.getUserDetails().getAuthorities(), AuthorityType.class);
		final String surname = model.getSurname();
		final String name = model.getName();
		final String login = model.getUsername();
		final UserData data = new UserData(login, name, surname, authorities);
		return data;
	}

    @Override
    public UserModel dataToModel(@NonNull UserData data) {
        final UserModel model = new UserModel();
        model.setUserDetails(new UserDetailsModel());
        return userPopulator.populate(data, model);
    }
}
