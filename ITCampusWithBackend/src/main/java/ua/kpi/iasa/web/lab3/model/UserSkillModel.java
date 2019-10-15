package ua.kpi.iasa.web.lab3.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
//@Entity
@Embeddable
//@Table(name = "user_skills")
public class UserSkillModel {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private Integer userSkillId;
//	@Column(name = "personal_info_id", nullable = false)
//	private Integer personalInfoId;
    @Column(name = "skill_name", nullable = false)
    private String name;
    @Column(name = "skill_assessment", nullable = false)
    private String assessment;
}
