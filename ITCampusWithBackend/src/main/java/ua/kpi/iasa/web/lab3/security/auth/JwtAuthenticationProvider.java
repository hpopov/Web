package ua.kpi.iasa.web.lab3.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import ua.kpi.iasa.web.lab3.security.JwtTokenService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	JwtTokenService jwtTokenService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
		final String token = jwtAuthentication.getToken();
		if (token == null) {
			return authentication;
		}
		jwtTokenService.validateToken(token);
		
		final JwtAuthentication finalJwtAuthentication = JwtAuthentication.builder().withToken(token)
				.withUsername(jwtTokenService.parseUsername(token))
				.withAuthorities(jwtTokenService.parseRoles(token))
				.build();
		finalJwtAuthentication.setAuthenticated(true);
		return finalJwtAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthentication.class.equals(authentication);
	}

}
