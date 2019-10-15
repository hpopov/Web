package ua.kpi.iasa.web.lab3.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectData {
    private String name;
    private String teacher;
    private String testType;
    private int subjectId;
    private int academicHours;
    private int mark;
}