package ua.kpi.iasa.web.lab3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "subjects", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "name"}))
public class SubjectModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int subjectId;

//	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH },
//			fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id", nullable = false)
//	private UserModel user;
	@Column(name = "user_id", updatable = false, nullable = false)
	private int userId;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "teacher", nullable = false)
	private String teacher;
	@Enumerated(EnumType.STRING)
	@Column(name = "test_type", nullable = false)
	private TestType testType;
	@Column(name = "academic_hours", nullable = false)
	private int academicHours;
	@Column(name = "mark", nullable = false)
	private int mark;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	public int getAcademicHours() {
		return academicHours;
	}

	public void setAcademicHours(int academicHours) {
		this.academicHours = academicHours;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
//
//	public UserModel getUser() {
//		return user;
//	}
//
//	public void setUser(UserModel user) {
//		this.user = user;
//	}

}
