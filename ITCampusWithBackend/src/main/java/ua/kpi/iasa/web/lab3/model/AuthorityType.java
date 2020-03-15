package ua.kpi.iasa.web.lab3.model;

import org.springframework.security.core.GrantedAuthority;

public enum AuthorityType implements GrantedAuthority, EnumType<AuthorityType, AuthorityModel> {

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

    @Override
    public AuthorityModel toEmptyModel(AuthorityType type) {
        AuthorityModel model = new AuthorityModel();
        model.setValue(type);
        return model;
    }

}
