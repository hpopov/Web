package ua.kpi.iasa.web.lab3.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicUserData {

    @Schema(required = true)
    private String login;

    @Schema(required = true)
    private String name;

    @Schema(required = true)
    private String surname;
}
