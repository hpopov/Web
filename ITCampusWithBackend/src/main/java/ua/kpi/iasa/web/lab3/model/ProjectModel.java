package ua.kpi.iasa.web.lab3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private Integer projectId;
	@Column(name = "header", nullable = false)
	private String header;
	@Column(name= "image_name", nullable = false)
	private String imageName;
	@Column(name = "description", nullable = false)
	private String description;

//	public UserModel getUserModel() {
//		return userModel;
//	}
//
//	public void setUserModel(UserModel userModel) {
//		this.userModel = userModel;
//	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public Integer getProjectId() {
		return projectId;
	}
	
	public String getHeader() {
		return header;
	}
	
	public void setHeader(String header) {
		this.header = header;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}