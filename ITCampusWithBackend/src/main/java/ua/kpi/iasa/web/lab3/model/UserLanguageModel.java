package ua.kpi.iasa.web.lab3.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
//@Entity
//@Table(name = "user_languages", uniqueConstraints = 
//@UniqueConstraint(columnNames = {"personal_info_id", "language" }))
@Embeddable
public class UserLanguageModel {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private Integer id;

//	@Column(name = "personal_info_id", updatable = false, nullable = false)
//	private Integer personalInfoId;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "assessment", nullable = false)
    private String assessment;
}
