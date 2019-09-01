package ua.kpi.iasa.web.lab3.rest.impl;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.converter.AuthorityConverter;
import ua.kpi.iasa.web.lab3.converter.UserConverter;
import ua.kpi.iasa.web.lab3.data.AuthData;
import ua.kpi.iasa.web.lab3.data.CredentialsData;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.rest.AuthenticationController;
import ua.kpi.iasa.web.lab3.security.JwtTokenService;
import ua.kpi.iasa.web.lab3.service.UserDetailsService;
import ua.kpi.iasa.web.lab3.service.UserService;

@RestController
@RequestMapping("/rest/authentication")
public class ApiAuthenticationController extends LoggableComponent implements AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityConverter authorityConverter;
	@Autowired
	private UserConverter userConverter;

	@PostMapping
	@Override
	public AuthData authenticate(@RequestBody CredentialsData credentials) {
		final String username = credentials.getUsername();
		try {
			authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(username, credentials.getPassword()));
		} catch (AuthenticationException e) {
			log.error("Error during Logging in", e);
			throw new BadCredentialsException("Invalid username/password supplied");
		}
		final Set<String> roles = userDetailsService.getAuthoritiesByUsername(username)
				.stream().map(authorityConverter::modelToData).map(GrantedAuthority::getAuthority)
				.collect(Collectors.toSet());
		String token = jwtTokenService.createToken(username, roles);
		AuthData userData = new AuthData(username, token);
		return userData;
	}

	@GetMapping
	@Override
	public UserData getCurrentUser(HttpServletRequest request) {
		final Principal userPrincipal = request.getUserPrincipal();
		return userConverter.modelToData(userService.getUserByUsername(userPrincipal.getName()));
	}

}
