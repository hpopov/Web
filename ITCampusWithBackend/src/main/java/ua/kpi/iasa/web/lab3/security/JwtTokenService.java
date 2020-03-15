package ua.kpi.iasa.web.lab3.security;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

@Service
public class JwtTokenService {

    private static final String ROLES_KEY = "roles";
    private static final String AUTHORIZATION = "Authorization";
    private static final String JWT_PREFIX = "Bearer ";

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:9000000}")
    private long validityInMilliseconds; // 150m

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, Collection<String> roles) {// temp

        Claims claims = Jwts.claims().setSubject(username);
        claims.put(ROLES_KEY, roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

//	public Authentication getAuthentication(String token) {///?
//		UserDetails userDetails = this.userDetailsService.loadUserByUsername(parseUsername(token));
//		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//	}

    public String parseUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Set<AuthorityType> parseRoles(String token) {// TODO
        Collection<?> roles = (Collection<?>) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                .get(ROLES_KEY);
        return roles.stream().map(String::valueOf).map(AuthorityType::valueOf).collect(Collectors.toSet());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(JWT_PREFIX)) {
            return bearerToken.substring(JWT_PREFIX.length(), bearerToken.length());
        }
        return null;
    }

    public void validateToken(String token) throws InvalidJwtAuthenticationException {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                throw new InvalidJwtAuthenticationException("Expired JWT token");
            }
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }

    public boolean isTokenValid(String token) {
        final Date tokenExpiredDate;
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            tokenExpiredDate = claims.getBody().getExpiration();
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
        if (tokenExpiredDate.before(new Date())) {
            return false;
        }
        return true;
    }
}
