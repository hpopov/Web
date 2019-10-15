package ua.kpi.iasa.web.lab3.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileData {
    private PersonalInfoData personalInfo;
    private List<SubjectData> subjects;
    private List<ProjectData> projects;
}
