package ua.kpi.iasa.web.lab3.data;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileData {
    
    @Schema(required = true)
    private PersonalInfoData personalInfo;

    @Schema(required = false)
    private List<SubjectData> subjects;

    @Schema(required = true)
    private List<ProjectData> projects;
}
