package ua.kpi.iasa.web.lab3;

import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ua.kpi.iasa.web.lab3.tokens.InvalidJwtAuthenticationException;
import ua.kpi.iasa.web.lab3.tokens.JwtTokenService;
import ua.kpi.iasa.web.lab3.users.SimpleUser;
import ua.kpi.iasa.web.lab3.users.UserService;
import ua.kpi.iasa.web.lab3.users.data.AuthData;
import ua.kpi.iasa.web.lab3.users.data.CredentialsData;
import ua.kpi.iasa.web.lab3.users.data.UserData;
import ua.kpi.iasa.web.lab3.users.data.UserFacade;

@RestController
@CrossOrigin("https://localhost:4200")
@EnableAutoConfiguration
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService jwtTokenService;	
    
    @Autowired
    UserService userService;

    @Autowired
	private UserFacade userFacade;
	
//	@GetMapping("/tordek")
//    public ModelAndView index() {
//        return new ModelAndView("index.html");
//    }
 
//    @RequestMapping("/login")
//    public boolean login(@RequestBody SimpleUser user) {
//    	System.out.println("Recieved /login request" + user);
//        return
//          user.getUsername().equals("tordek") && user.getPassword().equals("password");
//    }
	
    @PostMapping("/login")
    public AuthData signin(@RequestBody CredentialsData credentials) {

        String username = credentials.getUsername();
        try {
            authenticationManager
            	.authenticate(new UsernamePasswordAuthenticationToken(username, credentials.getPassword()));
        } catch (AuthenticationException e) {
        	LOG.error("Error during Logging in", e);
            throw new BadCredentialsException("Invalid username/password supplied");
        }
        final List<String> roles = userFacade.getUserAuthoritiesFromUsername(username);
		String token = jwtTokenService.createToken(username, roles);
        AuthData userData = new AuthData(username, token);
        return userData;
    }
    

    
    @GetMapping("/current-user")
    public UserData getCurrentUser(HttpServletRequest request) throws InvalidJwtAuthenticationException {
    	String token = jwtTokenService.resolveToken(request);
    	return userFacade.getUserDataFromToken(token);
    }
     
    @GetMapping("/user/{username}")
    public UserData getUserByLogin(@RequestParam String username) {
    	UserData userdata = userFacade.getUserDataByUsername(username);
    	return userdata;
    }
}
