package ua.kpi.iasa.web.lab3.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePersonalInfoData {

	private int userId;
	private PersonalInfoData personalInfo;
	

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)	
	public UpdatePersonalInfoData(@JsonProperty int userId,
			@JsonProperty PersonalInfoData personalInfo) {
		this.userId = userId;
		this.personalInfo = personalInfo;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public PersonalInfoData getPersonalInfo() {
		return personalInfo;
	}
	
	public void setPersonalInfo(PersonalInfoData personalInfo) {
		this.personalInfo = personalInfo;
	}
	
	
}
