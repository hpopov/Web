package ua.kpi.iasa.web.lab3.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "projects")
public class ProjectModel {
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
//			fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id", nullable = false, updatable = false)
//	private UserModel userModel;
    @Column(name = "user_id", updatable = false, nullable = false)
    private int userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "header", nullable = false)
    private String header;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "fileName", column = @Column(name = "image_name")),
            @AttributeOverride(name = "extension", column = @Column(name = "image_extension")) })
    private FilePathModel projectImage;

    @Column(name = "description", nullable = false)
    private String description;
}