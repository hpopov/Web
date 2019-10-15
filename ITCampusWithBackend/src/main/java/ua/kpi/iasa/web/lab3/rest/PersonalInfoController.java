package ua.kpi.iasa.web.lab3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.PersonalInfoConverter;
import ua.kpi.iasa.web.lab3.service.PersonalInfoService;

@RestController
@RequestMapping("/rest/personalInfos")
public class PersonalInfoController extends LoggableComponent {

    @Autowired
    private PersonalInfoService personalInfoService;
    @Autowired
    private PersonalInfoConverter personalInfoConverter;

    @GetMapping("/{username}")
    public PersonalInfoData getPersonalInfo(@PathVariable String username) {
        return personalInfoConverter.modelToData(personalInfoService.getPersonalInfoByUsername(username));
    }

    @PutMapping("/{username}")
    public PersonalInfoData updatePersonalInfo(@RequestBody PersonalInfoData personalInfoData,
            @PathVariable String username) {
        return personalInfoConverter.modelToData(personalInfoService.updatePersonalInfoByUsername(personalInfoData, username));
    }
}
