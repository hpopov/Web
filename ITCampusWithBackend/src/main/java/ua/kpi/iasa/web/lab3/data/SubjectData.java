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
public class SubjectData {

    @Schema(required = true)
    private String name;

    @Schema(required = true, description = "name of the teacher")
    private String teacher;

    @Schema(required = true, description = "defines whether it was exam or credit as final test")
    private String testType;

    @Schema(required = true)
    private int subjectId;

    @Schema(required = true, description = "quantity of academic hours read for this subject")
    private int academicHours;

    @Schema(required = true)
    private int mark;
}