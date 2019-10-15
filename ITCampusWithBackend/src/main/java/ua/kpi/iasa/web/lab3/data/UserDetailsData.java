package ua.kpi.iasa.web.lab3.data;

import java.util.EnumSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class UserDetailsData implements UserDetails, CredentialsContainer {
    private String username;
    private String password;
    private EnumSet<AuthorityType> authorities;

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
