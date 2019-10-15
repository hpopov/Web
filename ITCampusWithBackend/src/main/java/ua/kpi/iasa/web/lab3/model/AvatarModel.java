package ua.kpi.iasa.web.lab3.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "avatars")
public class AvatarModel {

    @Id
    @Column(name = "username")
    private String username;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "fileName", column = @Column(name = "image_name")),
            @AttributeOverride(name = "extension", column = @Column(name = "image_extension")) })
    private FilePathModel avatarImage;
}
