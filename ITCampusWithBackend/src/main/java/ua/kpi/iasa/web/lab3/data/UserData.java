package ua.kpi.iasa.web.lab3.data;

import java.util.EnumSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String login;
    private String name;
    private String surname;
    private EnumSet<AuthorityType> authorities;
}
