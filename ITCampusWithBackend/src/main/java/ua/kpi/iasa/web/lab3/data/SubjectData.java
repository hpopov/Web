package ua.kpi.iasa.web.lab3.data;

public class SubjectData {

	private String name;
	private String teacher;
	private String testType;
	private int subjectId;
	private int academicHours;
	private int mark;

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

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

//	public int getNum() {
//		return num;
//	}
//	
//	public void setNum(int num) {
//		this.num = num;
//	}

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

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

}