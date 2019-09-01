package ua.kpi.iasa.web.lab3.rest;

import ua.kpi.iasa.web.lab3.data.ProfileData;

public interface ProfileController {

	ProfileData getProfileByLogin(String login);
}
