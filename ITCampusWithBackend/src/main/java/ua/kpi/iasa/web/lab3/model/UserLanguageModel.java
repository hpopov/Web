package ua.kpi.iasa.web.lab3.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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

//	public Integer getPersonalInfoId() {
//		return personalInfoId;
//	}
//
//	public void setPersonalInfoId(Integer personalInfoId) {
//		this.personalInfoId = personalInfoId;
//	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAssessment() {
		return assessment;
	}

	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

}
