package ua.kpi.iasa.web.lab3.security.auth;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.Generated;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthentication extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private final String token;

	@Generated("SparkTools")
	private JwtAuthentication(Builder builder) {
		super(builder.authorities);
		this.username = builder.username;
		this.token = builder.token;
	}

	public JwtAuthentication(String token) {
		super(null);
		this.token = token;
	}

	/**
	 * JwtAuthentication is not intended to contain any credentials
	 *  thus it returns <code>null</code>
	 * @return null
	 */
	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Principal getPrincipal() {
		return new SimplePrincipal(username);
	}

	public String getToken() {
		return token;
	}

	
	/**
	 * Creates builder to build {@link JwtAuthentication}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link JwtAuthentication}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
		private String username;
		private String token;

		private Builder() {
		}

		public Builder withAuthorities(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = authorities;
			return this;
		}

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withToken(String token) {
			this.token = token;
			return this;
		}

		public JwtAuthentication build() {
			return new JwtAuthentication(this);
		}
	}

}
