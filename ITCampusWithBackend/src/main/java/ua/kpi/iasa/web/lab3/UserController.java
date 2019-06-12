package ua.kpi.iasa.web.lab3;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.kpi.iasa.web.lab3.data.AuthData;
import ua.kpi.iasa.web.lab3.data.CredentialsData;
import ua.kpi.iasa.web.lab3.data.PageData;
import ua.kpi.iasa.web.lab3.data.UserData;
import ua.kpi.iasa.web.lab3.facade.PageFacade;
import ua.kpi.iasa.web.lab3.facade.UserFacade;
import ua.kpi.iasa.web.lab3.token.InvalidJwtAuthenticationException;
import ua.kpi.iasa.web.lab3.token.JwtTokenService;

@RestController
@CrossOrigin("https://localhost:4200")
@EnableAutoConfiguration
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
	private UserFacade userFacade;
    @Autowired
    private PageFacade pageFacade;
	
//	@GetMapping("/tordek")
//    public ModelAndView index() {
//        return new ModelAndView("index.html");
//    }
 
    @GetMapping("/pageData")
    public PageData getPageDataForUser(@RequestParam String username, HttpServletRequest request) {
    	System.out.println("Recieve /pageData request from front!");
    	String token = jwtTokenService.resolveToken(request);
    	PageData pageData = pageFacade.makePageDataFromUsernameAndToken(username, token);
    	return pageData;
    }
	
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
