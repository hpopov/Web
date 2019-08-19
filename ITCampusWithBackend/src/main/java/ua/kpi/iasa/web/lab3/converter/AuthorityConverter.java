package ua.kpi.iasa.web.lab3.converter;

import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.model.AuthorityModel;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

@Component
public class AuthorityConverter implements Converter<AuthorityModel, AuthorityType>{

	@Override
	public AuthorityType modelToData(AuthorityModel model) {
		return model.getAuthorityRole();
	}

	@Override
	public AuthorityModel dataToModel(AuthorityType data) {
		AuthorityModel model = new AuthorityModel();
		model.setAuthorityRole(data);
		return model;
	}

}
