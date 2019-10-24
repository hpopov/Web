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
public class UpdateProjectData {

    @Schema(required = true)
    private Integer id;

    @Schema(required = true, description = "brief description or name of the project")
    private String header;

    @Schema(required = true)
    private String description;
}
