package ua.kpi.iasa.web.lab3.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Embeddable
public class FileModel {

    @Column(name = "name", nullable = false)
    private UUID fileName;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Temporal(TemporalType.DATE)
    @Column(name = "created")
    private Date created;
}
