package ua.kpi.iasa.web.lab3.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.dao.PersonalInfoDao;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.mapping.populator.impl.PersonalInfoPopulator;
import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.service.PersonalInfoService;
import ua.kpi.iasa.web.lab3.service.UserService;

@Service
public class DefaultPersonalInfoService extends LoggableComponent implements PersonalInfoService {

    @Autowired
    private UserService userService;
    @Autowired
    private PersonalInfoDao personalInfoDao;
    @Autowired
    private PersonalInfoPopulator personalInfoPopulator;

    @Override
    public PersonalInfoModel getPersonalInfoByUsername(String username) {
        final UserModel user = userService.getUserByUsername(username);
        return personalInfoDao.findByUserId(user.getId()).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public PersonalInfoModel updatePersonalInfoByUsername(PersonalInfoData personalInfo, String username) {
        final PersonalInfoModel personalInfoModel = personalInfoPopulator.populate(personalInfo,
                getPersonalInfoByUsername(username));
        return personalInfoDao.save(personalInfoModel);
    }
}
