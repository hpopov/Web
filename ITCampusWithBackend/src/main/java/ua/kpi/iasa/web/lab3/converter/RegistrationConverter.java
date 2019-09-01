package ua.kpi.iasa.web.lab3.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.data.RegistrationData;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class RegistrationConverter implements DataToModelConverter<UserModel, RegistrationData>{

	@Autowired
	private PublicUserConverter publicUserConverter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserModel dataToModel(RegistrationData data) {
		UserModel model = publicUserConverter.dataToModel(data.getUser());
		model.getUserDetails().setPassword(passwordEncoder.encode(data.getPassword()));
		final PersonalInfoModel personalInfo = new PersonalInfoModel();
		personalInfo.setUser(model);
		model.setPersonalInfo(personalInfo);
		return model;
	}

}
