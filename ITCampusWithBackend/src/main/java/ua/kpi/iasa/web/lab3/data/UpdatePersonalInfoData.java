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
public class UpdatePersonalInfoData {

    @Schema(required = true)
    private int userId;

    @Schema(required = true)
    private PersonalInfoData personalInfo;
}
