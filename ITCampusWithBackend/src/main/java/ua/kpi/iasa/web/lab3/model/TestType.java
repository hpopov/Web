package ua.kpi.iasa.web.lab3.model;

public enum TestType {
	EXAM("Exam"), CREDIT("Credit");
	
	private String presentation;

	private TestType(String presentation) {
		this.presentation = presentation;
	}
	
	@Override
	public String toString() {
		return presentation;
	}
}
