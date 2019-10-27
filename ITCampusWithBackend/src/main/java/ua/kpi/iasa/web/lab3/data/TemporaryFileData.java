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
public class TemporaryFileData {

    @Schema(required = true)
    private Integer id;

    @Schema(required = true, description = "The url to download the file")
    private String requestUrl;
}
