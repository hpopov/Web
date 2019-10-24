package ua.kpi.iasa.web.lab3.data;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoData {

    @Schema(required = true)
	private PublicUserData user;

    @Schema(required = true)
	private String phoneNumber;

    @Schema(required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

    @Schema(required = true)
	private String city;

    @Schema(required = true)
	private String education;

    @Schema(required = true)
	private String faculty;

    @Schema(required = true)
    private Integer firstEducationYear;

    @Schema(required = true)
    private Integer lastEducationYear;
    
    @Schema(required = true, description = "key stands for skill name, value stands for skill assessment")
	private Map<String, String> skills;
	
    @Schema(required = true, description = "key stands for language name, value stands for language assessment")
	private Map<String, String> languages;
}