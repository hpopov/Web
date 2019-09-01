package ua.kpi.iasa.web.lab3.model;

import java.util.EnumSet;
import java.util.function.Supplier;

import org.springframework.security.core.GrantedAuthority;

public enum AuthorityType implements GrantedAuthority {
	
	ROLE_USER, ROLE_ADMIN; 
	
//	private static final Map<String, AuthorityType> AUTH_MAP;
//	static {
//		for(AuthorityType value: values()) {
//			AUTH_MAP.put(value.getAuthority(), value);
//		}
//	}
//	
//	private AuthorityType(String authority) {
//		this.authority = authority;
//	}
	
//	private String authority;
	
	@Override
	public String getAuthority() {
		return toString();
	}

}
