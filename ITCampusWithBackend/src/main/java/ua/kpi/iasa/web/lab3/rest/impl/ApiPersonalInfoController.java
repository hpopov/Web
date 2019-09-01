package ua.kpi.iasa.web.lab3.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.converter.PersonalInfoConverter;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.rest.PersonalInfoController;
import ua.kpi.iasa.web.lab3.service.PersonalInfoService;

@RestController
@RequestMapping("/rest/personalInfos")
public class ApiPersonalInfoController extends LoggableComponent implements PersonalInfoController {

	@Autowired
	private PersonalInfoService personalInfoService;
	@Autowired
	private PersonalInfoConverter personalInfoConverter;
	
	@GetMapping("/{username}")
	@Override
	public PersonalInfoData getPersonalInfo(@PathVariable String username) {
		return personalInfoConverter.modelToData(personalInfoService.getPersonalInfoByUsername(username));
	}

	@PostMapping("/{username}")
	@Override
	public boolean updatePersonalInfo(@RequestBody PersonalInfoData personalInfoData,
			@PathVariable String username) {
		return personalInfoService.updatePersonalInfoByUsername(personalInfoData, username);
	}

}
