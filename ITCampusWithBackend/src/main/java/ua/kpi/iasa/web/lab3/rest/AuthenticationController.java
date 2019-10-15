package ua.kpi.iasa.web.lab3.rest;

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
import ua.kpi.iasa.web.lab3.data.AuthData;
import ua.kpi.iasa.web.lab3.data.CredentialsData;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.UserConverter;
import ua.kpi.iasa.web.lab3.security.JwtTokenService;
import ua.kpi.iasa.web.lab3.service.UserService;

@RestController
@RequestMapping("/rest/authentication")
public class AuthenticationController extends LoggableComponent {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @PostMapping
    public AuthData authenticate(@RequestBody CredentialsData credentials) {
        final String username = credentials.getUsername();
        final String password = credentials.getPassword();
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                username, password);

        try {
            authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            log.error("Error during logging in", e);
            throw new BadCredentialsException("Invalid username/password supplied");
        }

        final Set<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
//				userDetailsService.getAuthoritiesByUsername(username)
//				.stream().map(authorityConverter::modelToData).map(GrantedAuthority::getAuthority)
//				.collect(Collectors.toSet());///REFACTOR!!! See above
        String token = jwtTokenService.createToken(username, roles);
        AuthData userData = new AuthData(username, token);
        return userData;
    }

    @GetMapping
    public UserData getCurrentUser(HttpServletRequest request) {
        final Principal userPrincipal = request.getUserPrincipal();
        return userConverter.modelToData(userService.getUserWithDetailsByUsername(userPrincipal.getName()));
    }

}
