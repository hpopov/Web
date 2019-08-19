package ua.kpi.iasa.web.lab3.converter;

import java.util.EnumSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.data.UserDetailsData;
import ua.kpi.iasa.web.lab3.model.AuthorityType;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;

@Component
public class UserDetailsConverter implements Converter<UserDetailsModel, UserDetailsData>{

	@Autowired
	private AuthorityConverter authorityConverter;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetailsData modelToData(UserDetailsModel model) {
		final EnumSet<AuthorityType> authorities = model.getAuthorities().stream()
				.map(authorityConverter::modelToData)
				.collect(Collectors.toCollection(AuthorityType.emptySet));
		final String password = model.getPassword();
		final String username = model.getUsername();
		UserDetailsData data = new UserDetailsData(username, password, authorities);
		return data;
	}

	@Override
	public UserDetailsModel dataToModel(UserDetailsData data) {
		UserDetailsModel model = new UserDetailsModel();
		model.setUsername(data.getUsername());
		model.setPassword(passwordEncoder.encode(data.getPassword()));
		model.setAuthorities(data.getAuthorities().stream()
				.map(authorityConverter::dataToModel).collect(Collectors.toSet()));
		return model;
	}

}
