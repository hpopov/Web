package ua.kpi.iasa.web.lab3.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(targetEntity = PersonalInfoModel.class, mappedBy = "user", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private PersonalInfoModel personalInfo;
	@OneToMany(targetEntity = SubjectModel.class, mappedBy = "userId", cascade = { CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<SubjectModel> subjects;
	@OneToMany(targetEntity = ProjectModel.class, mappedBy = "userId", cascade = { CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<ProjectModel> projects;
	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "username", nullable = false, unique = true, updatable = false, insertable = false)
	private String username;

	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE,
			CascadeType.PERSIST},
			fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "username")
	private UserDetailsModel userDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public UserDetailsModel getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsModel userDetails) {
		this.userDetails = userDetails;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public PersonalInfoModel getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfoModel personalInfo) {
		this.personalInfo = personalInfo;
	}

	public List<SubjectModel> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectModel> subjects) {
		this.subjects = subjects;
	}

	public List<ProjectModel> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectModel> projects) {
		this.projects = projects;
	}

}