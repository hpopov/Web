package ua.kpi.iasa.web.lab3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.kpi.iasa.web.lab3.data.PersonalInfoData;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.PersonalInfoConverter;
import ua.kpi.iasa.web.lab3.rest.apiresponses.NotFoundApiResponse;
import ua.kpi.iasa.web.lab3.rest.apiresponses.UnauthorizedApiResponse;
import ua.kpi.iasa.web.lab3.service.PersonalInfoService;

@Tag(name = "personalInfo", description = "user personal information")
@UnauthorizedApiResponse
@NotFoundApiResponse
@RestController
@RequestMapping(path = "/rest/personalInfos")
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService personalInfoService;
    @Autowired
    private PersonalInfoConverter personalInfoConverter;

    @Operation(summary = "Get personal information for specified user by username")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonalInfoData getPersonalInfo(@PathVariable String username) {
        return personalInfoConverter.modelToData(personalInfoService.getPersonalInfoByUsername(username));
    }

    @Operation(summary = "Update personal information for user by login")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @PutMapping(path = "/{username}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonalInfoData updatePersonalInfo(@RequestBody PersonalInfoData personalInfoData,
            @PathVariable String username) {
        return personalInfoConverter
                .modelToData(personalInfoService.updatePersonalInfoByUsername(personalInfoData, username));
    }
}
