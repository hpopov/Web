package ua.kpi.iasa.web.lab3;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
@EnableAutoConfiguration
public class UserController {
 
    @RequestMapping("/login")
    public boolean login(@RequestBody SimpleUser user) {
    	System.out.println("Recieved /login request" + user);
        return
          user.getUsername().equals("user") && user.getPassword().equals("password");
    }
     
//    @RequestMapping("/user")
//    public Principal user(HttpServletRequest request) {
//        String authToken = request.getHeader("Authorization")
//          .substring("Basic".length()).trim();
//        return () ->  new String(Base64.getDecoder()
//          .decode(authToken)).split(":")[0];
//    }
}