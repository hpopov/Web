package ua.kpi.iasa.web.lab3.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import ua.kpi.iasa.web.lab3.security.JwtTokenService;
import ua.kpi.iasa.web.lab3.security.auth.JwtAuthentication;

public class JwtTokenFilter extends GenericFilterBean {

    private JwtTokenService jwtTokenService;

    public JwtTokenFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

//    	HttpServletRequest httpReq = (HttpServletRequest) req;
//        String token = jwtTokenService.resolveToken(httpReq);
//        if (token != null && jwtTokenService.isTokenValid(token)) {
//            Authentication auth = jwtTokenService.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//        filterChain.doFilter(req, res);
        HttpServletRequest httpReq = (HttpServletRequest) req;
        String token = jwtTokenService.resolveToken(httpReq);
        if (token != null && jwtTokenService.isTokenValid(token)) {
            SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(token));
        }
        filterChain.doFilter(req, res);
    }

}
