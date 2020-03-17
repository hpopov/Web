package ua.kpi.iasa.web.lab3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ua.kpi.iasa.web.lab3.security.JwtConfigurer;
import ua.kpi.iasa.web.lab3.security.auth.JwtAuthenticationProvider;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity
//@CrossOrigin(origins = "*")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtConfigurer jwtConfigurer;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.TRACE);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
		http.httpBasic().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/index.html", "/", "/login",
						"/*.js", "/*.css", "/*.js.map", "/assets/**").permitAll()
				.antMatchers(HttpMethod.GET, "/rest/users", "/rest/users/*","/rest/users/*/avatar", "/resources/**",
				        "/v3/api-docs", "/v3/api-docs.yml").permitAll()
				.antMatchers(HttpMethod.POST, "/rest/authentication", "/rest/users").permitAll()
//				.antMatchers(HttpMethod.PUT, "/rest/users").permitAll()
//				.antMatchers(HttpMethod.DELETE, "/rest/users/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/rest/authentication").hasRole("USER")
//                .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
				.anyRequest().authenticated().and().apply(jwtConfigurer).and().cors().and().exceptionHandling()
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and().headers()
				.httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000);

		// @formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider).userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
//			.inMemoryAuthentication();
//			.
//			.withUser("user")
//			.password("password")
//			.roles("USER");
    }

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
