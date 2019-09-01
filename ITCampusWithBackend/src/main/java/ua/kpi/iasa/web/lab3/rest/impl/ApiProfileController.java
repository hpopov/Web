package ua.kpi.iasa.web.lab3.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.data.ProfileData;
import ua.kpi.iasa.web.lab3.facade.ProfileFacade;
import ua.kpi.iasa.web.lab3.rest.ProfileController;

@RestController
@RequestMapping("/rest/profiles")
public class ApiProfileController extends LoggableComponent implements ProfileController {

	@Autowired
	private ProfileFacade profileFacade;
	
	
	@GetMapping("/{login}")
	@Override
	public ProfileData getProfileByLogin(@PathVariable String login) {
		return profileFacade.getProfileByUsername(login);
	}

}
