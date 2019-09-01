package ua.kpi.iasa.web.lab3.converter;

import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class PublicUserConverter implements Converter<UserModel, PublicUserData>{

	@Override
	public PublicUserData modelToData(UserModel model) {
		String surname = model.getSurname();
		String name = model.getName();
		String login = model.getUsername();
		return new PublicUserData(login, name, surname);
	}

	@Override
	public UserModel populateDataToModel(UserModel model, PublicUserData data) {
		if (model == null) {
			model = new UserModel();
		}
		model.setName(data.getName());
		model.setSurname(data.getSurname());
		model.setUsername(data.getLogin());
		UserDetailsModel userDetailsModel = model.getUserDetails() == null?
				new UserDetailsModel() : model.getUserDetails();
		userDetailsModel.setUsername(data.getLogin());
		model.setUserDetails(userDetailsModel);
		return model;
	}

}
