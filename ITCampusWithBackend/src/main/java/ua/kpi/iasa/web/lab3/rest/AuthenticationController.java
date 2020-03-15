package ua.kpi.iasa.web.lab3.rest;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ua.kpi.iasa.web.lab3.data.AuthData;
import ua.kpi.iasa.web.lab3.data.CredentialsData;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.UserConverter;
import ua.kpi.iasa.web.lab3.rest.apiresponses.NotFoundApiResponse;
import ua.kpi.iasa.web.lab3.rest.apiresponses.UnauthorizedApiResponse;
import ua.kpi.iasa.web.lab3.security.JwtTokenService;
import ua.kpi.iasa.web.lab3.service.UserService;

@Tag(name = "authentication", description = "authentication API")
@RestController
@RequestMapping(path = "/rest/authentication")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @Operation(summary = "Recieve the authentcation token")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @NotFoundApiResponse
    @ApiResponse(responseCode = "401", description = "Bad credentials", content = @Content)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthData authenticate(@RequestBody CredentialsData credentials) {
        final String username = credentials.getUsername();
        final String password = credentials.getPassword();
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                password);

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

    @Operation(summary = "Get currently authenticated user")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @UnauthorizedApiResponse
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserData getCurrentUser(HttpServletRequest request) {
        final Principal userPrincipal = request.getUserPrincipal();
        return userConverter.modelToData(userService.getUserWithDetailsByUsername(userPrincipal.getName()));
    }

}
