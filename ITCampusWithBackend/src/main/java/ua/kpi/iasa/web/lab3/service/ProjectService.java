package ua.kpi.iasa.web.lab3.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.multipart.MultipartFile;

import ua.kpi.iasa.web.lab3.data.CreateProjectData;
import ua.kpi.iasa.web.lab3.data.UpdateProjectData;
import ua.kpi.iasa.web.lab3.model.ProjectModel;

public interface ProjectService {

    List<ProjectModel> getProjectsByUsername(String username);

    ProjectModel createUserProject(String username, CreateProjectData project, MultipartFile image)
            throws EntityNotFoundException;

    ProjectModel updateUserProject(String username, UpdateProjectData project) throws EntityNotFoundException;

    ProjectModel updateUserProjectImage(String username, Integer projectId, MultipartFile image)
            throws EntityNotFoundException;

    void deleteUserProjectById(String username, Integer projectId) throws EntityNotFoundException;
}