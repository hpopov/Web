package ua.kpi.iasa.web.lab3.mapping.populator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.UserLanguageConverter;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.UserSkillConverter;
import ua.kpi.iasa.web.lab3.mapping.populator.Populator;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;

@Component
public class PersonalInfoPopulator implements Populator<PersonalInfoModel, PersonalInfoData> {

    @Autowired
    private UserLanguageConverter userLanguageConverter;
    @Autowired
    private UserSkillConverter userSkillConverter;
    @Autowired
    private PublicUserPopulator publicUserPopulator;

    @Override
    public PersonalInfoModel populate(@NonNull PersonalInfoData data, @NonNull PersonalInfoModel model) {
        model.setCity(data.getCity());
        model.setDateOfBirth(data.getDateOfBirth());
        model.setEducation(data.getEducation());
        model.setFaculty(data.getFaculty());
        model.setStartEducationYear(data.getFirstEducationYear());
        model.setFinishEducationYear(data.getLastEducationYear());
        model.setLanguages(Converter.mapToSet(userLanguageConverter::dataToModel, data.getLanguages().entrySet()));
        model.setSkills(Converter.mapToSet(userSkillConverter::dataToModel, data.getSkills().entrySet()));
        model.setPhoneNumber(data.getPhoneNumber());
        model.setUser(publicUserPopulator.populate(data.getUser(), model.getUser()));
        return model;
    }
}
