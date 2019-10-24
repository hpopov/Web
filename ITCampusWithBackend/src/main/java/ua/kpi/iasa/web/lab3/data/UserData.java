package ua.kpi.iasa.web.lab3.data;

import java.util.EnumSet;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.iasa.web.lab3.model.AuthorityType;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    
    @Schema(required = true)
    private String login;
    
    @Schema(required = true)
    private String name;
    
    @Schema(required = true)
    private String surname;
    
    @Schema(required = true, description = "set of granted authorities")
    private EnumSet<AuthorityType> authorities;
}
