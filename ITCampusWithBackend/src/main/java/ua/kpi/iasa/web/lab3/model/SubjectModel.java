package ua.kpi.iasa.web.lab3.model;

public class SubjectModel {
	
	private int subjectId;
	private int userId;
	private String name;
	private String teacher;
	private TestType testType;
	private int academicHours;
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
	
}
