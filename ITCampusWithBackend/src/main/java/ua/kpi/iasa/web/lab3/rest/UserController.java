package ua.kpi.iasa.web.lab3.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ua.kpi.iasa.web.lab3.data.AuthData;
import ua.kpi.iasa.web.lab3.data.CredentialsData;
import ua.kpi.iasa.web.lab3.data.PageData;
import ua.kpi.iasa.web.lab3.data.UpdatePersonalInfoData;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.security.InvalidJwtAuthenticationException;

public interface UserController {
	
	@GetMapping("/pageData")
	public PageData getPageDataForUser(@RequestParam String username, HttpServletRequest request);

	@PostMapping("/login")
	AuthData signin(@RequestBody CredentialsData credentials);

	@GetMapping("/current-user")
	UserData getCurrentUser(HttpServletRequest request) throws InvalidJwtAuthenticationException;

	@GetMapping("/user/{username}")
	UserData getUserByLogin(@RequestParam String username);
	
	@PostMapping("/updateUser")
	boolean updateUserById(@RequestBody UserData user);
	
	@PostMapping("/updatePersonalInfo")
	boolean updatePersonalInfoById(@RequestBody UpdatePersonalInfoData updatePersonalInfo);
}
