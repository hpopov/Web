package ua.kpi.iasa.web.lab3.mapping.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.PersonalInfoPopulator;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Component
public class PersonalInfoConverter implements Converter<PersonalInfoModel, PersonalInfoData> {

    @Autowired
    private UserLanguageConverter userLanguageConverter;
    @Autowired
    private UserSkillConverter userSkillConverter;
    @Autowired
    private PublicUserConverter publicUserConverter;
    @Autowired
    private PersonalInfoPopulator personalInfoPopulator;

    @Override
    public PersonalInfoData modelToData(@NonNull PersonalInfoModel model) {
        final PersonalInfoData data = new PersonalInfoData();
        data.setCity(model.getCity());
        data.setDateOfBirth(model.getDateOfBirth());
        data.setEducation(model.getEducation());
        data.setEducationBeginYear(model.getStartEducationYear());
        data.setEducationEndYear(model.getFinishEducationYear());
        data.setFaculty(model.getFaculty());
        data.setPhoneNumber(model.getPhoneNumber());
        data.setSkills(Converter.mapToList(userSkillConverter::modelToData, model.getSkills()));
        data.setLanguages(Converter.mapToList(userLanguageConverter::modelToData, model.getLanguages()));
        data.setUser(publicUserConverter.modelToData(model.getUser()));
        return data;
    }

    @Override
    public PersonalInfoModel dataToModel(@NonNull PersonalInfoData data) {
        final PersonalInfoModel model = new PersonalInfoModel();
        model.setUser(new UserModel());
        return personalInfoPopulator.populate(data, model);
    }
}
