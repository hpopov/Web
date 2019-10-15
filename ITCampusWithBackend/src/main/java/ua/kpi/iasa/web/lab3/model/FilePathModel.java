package ua.kpi.iasa.web.lab3.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class FilePathModel {
    
    @Column(name = "name", nullable = false)
    private UUID fileName;
    
    @Column(name = "extension", nullable = false)
    private String extension;
}
