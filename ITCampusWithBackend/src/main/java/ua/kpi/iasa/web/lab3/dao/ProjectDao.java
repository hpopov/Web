package ua.kpi.iasa.web.lab3.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

import ua.kpi.iasa.web.lab3.model.ProjectModel;

@RepositoryDefinition(domainClass = ProjectModel.class, idClass = Integer.class)
public interface ProjectDao {

    List<ProjectModel> findAllByUserId(Integer userId);

//	ProjectModel createUserProject(UserModel user, ProjectData project) throws DaoException;
//	Optional<ProjectModel> updateUserProjectById(UserModel user, ProjectData project) throws DaoException;
    ProjectModel save(ProjectModel project);

    void delete(ProjectModel project);

    Optional<ProjectModel> findByIdAndUserId(Integer id, Integer userId);

    void deleteByIdAndUserId(Integer id, Integer userId);
}
