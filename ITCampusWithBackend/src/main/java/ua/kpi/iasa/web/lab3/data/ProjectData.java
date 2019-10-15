package ua.kpi.iasa.web.lab3.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectData {
    private Integer id;
    private String header;
    private String imageUrl;
    private String description;
}