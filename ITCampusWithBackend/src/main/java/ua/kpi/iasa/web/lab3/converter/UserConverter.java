package ua.kpi.iasa.web.lab3.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

public class UserConverter implements Converter<UserModel, UserData>{

	@Autowired
	private UserDetailsConverter userDetailsConverter;
	
	@Autowired
	private AuthorityConverter authorityConverter;
	
	@Override
	public UserData modelToData(UserModel model) {
		final List<String> authorities = model.getUserDetails().getAuthorities().stream()
				.map(authorityConverter::modelToData).map(String::valueOf)
				.collect(Collectors.toList());
		final String surname = model.getSurname();
		final String name = model.getName();
		final String login = model.getUserDetails().getUsername();
		final int id = model.getId();
		final UserData data = new UserData(id, login, name, surname, authorities);
		return data;
	}

	@Override
	public UserModel dataToModel(UserData data) {
		UserModel model = new UserModel();
		model.setId(data.getId());
		model.setName(data.getName());
		model.setSurname(data.getSurname());
		UserDetailsModel userDetailsModel = new UserDetailsModel();
		userDetailsModel.setUsername(data.getLogin());
		model.setUserDetails(userDetailsModel);
		return model;
	}

}
