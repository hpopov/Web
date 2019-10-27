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
public class CreateProjectData {

    @Schema(required = true)
    private String header;

    @Schema(required = true)
    private String description;

    @Schema(required = true, description = "An id of temporary file for this user to become the image of the project")
    private Integer temporaryFileId;
}
