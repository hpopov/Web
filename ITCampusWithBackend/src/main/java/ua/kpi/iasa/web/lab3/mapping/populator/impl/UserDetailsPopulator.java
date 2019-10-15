package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.UserDetailsData;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.AuthorityConverter;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.UserDetailsModel;

@Component
public class UserDetailsPopulator implements Populator<UserDetailsModel, UserDetailsData> {

    @Autowired
    private AuthorityConverter authorityConverter;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetailsModel populate(@NonNull UserDetailsData data, @NonNull UserDetailsModel model) {
        model.setUsername(data.getUsername());
        model.setPassword(passwordEncoder.encode(data.getPassword()));
        model.setAuthorities(data.getAuthorities().stream()
                .map(authorityConverter::dataToModel).collect(Collectors.toSet()));
        return model;
    }
}
