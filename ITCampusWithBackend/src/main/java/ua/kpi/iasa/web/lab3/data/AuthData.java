package ua.kpi.iasa.web.lab3.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthData {
    
    @Schema(description = "username by which the user can be identified", required = true)
    private String username;

    @Schema(description = "authentication JWT", required = true)
    private String token;
}
