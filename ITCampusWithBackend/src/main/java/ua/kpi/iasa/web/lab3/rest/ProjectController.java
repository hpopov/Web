package ua.kpi.iasa.web.lab3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import ua.kpi.iasa.web.lab3.data.CreateProjectData;
import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.data.UpdateProjectData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.ProjectConverter;
import ua.kpi.iasa.web.lab3.service.ProjectService;

@Slf4j
@RestController
@RequestMapping("/rest/users/{username}/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectConverter projectConverter;

    @PostMapping(consumes = { "multipart/form-data" })
    public ProjectData createUserProject(@PathVariable String username, @RequestPart CreateProjectData project,
            @RequestPart MultipartFile image) {
        log.debug("CREATING PROJECT!!!!!!!!");
        return projectConverter.modelToData(projectService.createUserProject(username, project, image));
    }

    @GetMapping
    public Iterable<ProjectData> getUserProjects(@PathVariable String username) {
        return Converter.mapToList(projectConverter::modelToData, projectService.getProjectsByUsername(username));
    }

    @PutMapping("/{projectId}")
    public ProjectData updateUserProject(@PathVariable String username, @PathVariable Integer projectId,
            @RequestBody UpdateProjectData project) {
        project.setId(projectId);
        return projectConverter.modelToData(projectService.updateUserProject(username, project));
    }

    @DeleteMapping("/{projectId}")
    public void deleteUserProject(@PathVariable String username, @PathVariable Integer projectId) {
        projectService.deleteUserProjectById(username, projectId);
    }
}
