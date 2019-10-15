package ua.kpi.iasa.web.lab3.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePersonalInfoData {
    private int userId;
    private PersonalInfoData personalInfo;
}
