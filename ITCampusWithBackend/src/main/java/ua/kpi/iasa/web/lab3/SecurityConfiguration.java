package ua.kpi.iasa.web.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import ua.kpi.iasa.web.lab3.token.JwtConfigurer;
import ua.kpi.iasa.web.lab3.token.JwtTokenService;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenService jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/", "/user/*", "/home", "/login",
                		"/pageData", "/admin", "/adminPage.jsp",
    					"/*.js", "/*.css", "/*.js.map", "/assets/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider))
            .and().cors()
            .and()
            .headers()
                .httpStrictTransportSecurity()
                    .includeSubDomains(true)
                    .maxAgeInSeconds(31536000);
        //@formatter:on
    }
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		auth
//			.inMemoryAuthentication();
////			.
////			.withUser("user")
////			.password("password")
////			.roles("USER");
//	}

//	@Override
//	protected void configure(HttpSecurity http) 
//			throws Exception {
//		http.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/index.html", "/", "/tordek", "/home", "/login",
//					"/*.js", "/*.css", "/*.js.map", "/assets/**/*").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.httpBasic();
////			.formLogin()
////			.loginPage("/login");
//	}
    

}
