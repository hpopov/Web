package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.UserDetailsData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.UserDetailsPopulator;
import ua.kpi.iasa.web.lab3.model.AuthorityType;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;

@Component
public class UserDetailsConverter implements Converter<UserDetailsModel, UserDetailsData>{

	@Autowired
	private AuthorityConverter authorityConverter;
	@Autowired
	private UserDetailsPopulator userDetailsPopulator;
	
	@Override
	public UserDetailsData modelToData(@NonNull UserDetailsModel model) {
		final EnumSet<AuthorityType> authorities = Converter.mapToEnumSet(
				authorityConverter::modelToData, model.getAuthorities(), AuthorityType.class);
		final String password = model.getPassword();//LEFT DECODED PASSWORD!!!
		final String username = model.getUsername();
		UserDetailsData data = new UserDetailsData(username, password, authorities);
		return data;
	}

    @Override
    public UserDetailsModel dataToModel(@NonNull UserDetailsData data) {
        return userDetailsPopulator.populate(data, new UserDetailsModel());
    }
}
