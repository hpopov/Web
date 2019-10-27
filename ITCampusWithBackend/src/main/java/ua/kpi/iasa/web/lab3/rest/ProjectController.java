package ua.kpi.iasa.web.lab3.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.kpi.iasa.web.lab3.data.ApiExceptionData;
import ua.kpi.iasa.web.lab3.data.CreateProjectData;
import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.data.UpdateProjectData;
import ua.kpi.iasa.web.lab3.exception.UnauthorizedException;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.ProjectConverter;
import ua.kpi.iasa.web.lab3.rest.apiresponses.NotFoundApiResponse;
import ua.kpi.iasa.web.lab3.rest.apiresponses.UnauthorizedApiResponse;
import ua.kpi.iasa.web.lab3.service.ProjectService;

@Tag(name = "project", description = "user projects API")
@UnauthorizedApiResponse
@NotFoundApiResponse
@RestController
@RequestMapping("/rest/users/{username}/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectConverter projectConverter;

    @Operation(summary = "Create new project for user with specified username")
    @ApiResponse(responseCode = "201", description = "Successful operation")
    @ApiResponse(responseCode = "400", description = "Bad request: specified temporary file is not a suitable image",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiExceptionData.class)))
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectData createUserProject(@PathVariable String username, @RequestBody CreateProjectData project,
            HttpServletRequest request) {
        assertAuthenticatedUserEqualsToSpecified(username, request);
        return projectConverter.modelToData(projectService.createUserProject(username, project));
    }

    private void assertAuthenticatedUserEqualsToSpecified(String username, HttpServletRequest request) {
        if (false == username.equals(request.getUserPrincipal().getName())) {
            throw new UnauthorizedException();
        }
    }

    @Operation(summary = "Get all user projects by username")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectData> getUserProjects(@PathVariable String username) {
        return Converter.mapToList(projectConverter::modelToData, projectService.getProjectsByUsername(username));
    }

    @Operation(summary = "Update user project by username and project id")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @PutMapping(path = "/{projectId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectData updateUserProject(@PathVariable String username, @PathVariable Integer projectId,
            @RequestBody UpdateProjectData project, HttpServletRequest request) {
        assertAuthenticatedUserEqualsToSpecified(username, request);
        project.setId(projectId);
        return projectConverter.modelToData(projectService.updateUserProject(username, project));
    }

    @Operation(summary = "Update the image of user project by username and project id")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @PutMapping(path = "/{projectId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectData updateUserProjectImage(@PathVariable String username, @PathVariable Integer projectId,
            @RequestPart MultipartFile image, HttpServletRequest request) {
        assertAuthenticatedUserEqualsToSpecified(username, request);
        return projectConverter.modelToData(projectService.updateUserProjectImage(username, projectId, image));
    }

    @Operation(summary = "Delete user project by username and project id")
    @ApiResponse(responseCode = "204", description = "Successful operation")
    @DeleteMapping(path = "/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserProject(@PathVariable String username, @PathVariable Integer projectId,
            HttpServletRequest request) {
        assertAuthenticatedUserEqualsToSpecified(username, request);
        projectService.deleteUserProjectById(username, projectId);
    }
}
