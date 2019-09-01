package ua.kpi.iasa.web.lab3.converter;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.AuthorityType;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class UserConverter implements Converter<UserModel, UserData>{
	
	@Autowired
	private AuthorityConverter authorityConverter;
	
	@Override
	public UserData modelToData(UserModel model) {
		final EnumSet<AuthorityType> authorities = Converter.mapCollectionToEnumSet(authorityConverter::modelToData,
				model.getUserDetails().getAuthorities(), AuthorityType.class);
		final String surname = model.getSurname();
		final String name = model.getName();
		final String login = model.getUsername();
		final UserData data = new UserData(login, name, surname, authorities);
		return data;
	}

	@Override
	public UserModel populateDataToModel(UserModel model, UserData data) {
		if (model == null) {
			model = new UserModel();
		}
		model.setName(data.getName());
		model.setSurname(data.getSurname());
		model.setUsername(data.getLogin());
		UserDetailsModel userDetailsModel = model.getUserDetails() == null?
				new UserDetailsModel() : model.getUserDetails();
		userDetailsModel.setUsername(data.getLogin());
		userDetailsModel.setAuthorities(Converter.mapCollectionToSet(authorityConverter::dataToModel,
				data.getAuthorities()));
		model.setUserDetails(userDetailsModel);
		return model;
	}
}
