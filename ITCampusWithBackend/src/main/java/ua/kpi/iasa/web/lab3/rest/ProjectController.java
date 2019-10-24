package ua.kpi.iasa.web.lab3.rest;

import java.util.List;

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
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.kpi.iasa.web.lab3.data.CreateProjectData;
import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.data.UpdateProjectData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.ProjectConverter;
import ua.kpi.iasa.web.lab3.rest.apiresponses.NotFoundApiResponse;
import ua.kpi.iasa.web.lab3.rest.apiresponses.UnauthorizedApiResponse;
import ua.kpi.iasa.web.lab3.service.ProjectService;

@Tag(name = "project", description = "user projects API")
@UnauthorizedApiResponse
@NotFoundApiResponse
@Slf4j
@RestController
@RequestMapping("/rest/users/{username}/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectConverter projectConverter;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class CreateProjectRequestSchema {
        @Schema(required = true, type = "string", format = "base64")
        private MultipartFile image;

        @Schema(required = true)
        private CreateProjectData project;
    }

    @Operation(summary = "Create new project for user with specified username",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "",
                    content = @Content(
                            encoding = { @Encoding(name = "project", contentType = MediaType.APPLICATION_JSON_VALUE) },
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = CreateProjectRequestSchema.class))))
    @ApiResponse(responseCode = "201", description = "Successful operation")
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE },
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectData createUserProject(@PathVariable String username, @RequestPart MultipartFile image,
            @RequestPart CreateProjectData project) {
        log.debug("CREATING PROJECT!!!!!!!!");
        return projectConverter.modelToData(projectService.createUserProject(username, project, image));
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
            @RequestBody UpdateProjectData project) {
        project.setId(projectId);
        return projectConverter.modelToData(projectService.updateUserProject(username, project));
    }

    @Operation(summary = "Delete user project by username and project id")
    @ApiResponse(responseCode = "204", description = "Successful operation")
    @DeleteMapping(path = "/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserProject(@PathVariable String username, @PathVariable Integer projectId) {
        projectService.deleteUserProjectById(username, projectId);
    }
}
