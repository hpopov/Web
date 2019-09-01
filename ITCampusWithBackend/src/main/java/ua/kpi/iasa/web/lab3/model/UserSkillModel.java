package ua.kpi.iasa.web.lab3.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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

//	public Integer getPersonalInfoId() {
//		return personalInfoId;
//	}
//
//	public void setPersonalInfoId(Integer personalInfoId) {
//		this.personalInfoId = personalInfoId;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAssessment() {
		return assessment;
	}

	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}
//
//	public Integer getUserSkillId() {
//		return userSkillId;
//	}
//
//	public void setUserSkillId(Integer userSkillId) {
//		this.userSkillId = userSkillId;
//	}

}
