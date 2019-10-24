package ua.kpi.iasa.web.lab3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.kpi.iasa.web.lab3.data.ProfileData;
import ua.kpi.iasa.web.lab3.facade.ProfileFacade;
import ua.kpi.iasa.web.lab3.rest.apiresponses.NotFoundApiResponse;
import ua.kpi.iasa.web.lab3.rest.apiresponses.UnauthorizedApiResponse;

@Tag(name = "profile", description = "user profile: the bunch of user data such as personalInfo, projects, etc.")
@UnauthorizedApiResponse
@NotFoundApiResponse
@RestController
@RequestMapping(path = "/rest/profiles")
public class ProfileController {

    @Autowired
    private ProfileFacade profileFacade;

    @Operation(summary = "Get profile for specified user by username")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfileData getProfileByLogin(@PathVariable String username) {
        return profileFacade.getProfileByUsername(username);
    }
}
