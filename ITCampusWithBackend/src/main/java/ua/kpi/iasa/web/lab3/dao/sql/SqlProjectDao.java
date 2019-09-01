package ua.kpi.iasa.web.lab3.dao.sql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.iasa.web.lab3.converter.ProjectConverter;
import ua.kpi.iasa.web.lab3.dao.DaoException;
import ua.kpi.iasa.web.lab3.dao.ProjectDao;
import ua.kpi.iasa.web.lab3.data.ProjectData;
import ua.kpi.iasa.web.lab3.model.ProjectModel;
import ua.kpi.iasa.web.lab3.model.UserModel;

@Primary
@Repository
public class SqlProjectDao extends AbstractSqlDao implements ProjectDao {

	@Autowired
	private ProjectConverter projectConverter;
	
	@Transactional
	@Override
	public List<ProjectModel> getAllUserProjects(UserModel user) throws DaoException {
		return user.getProjects();
	}

	@Transactional
	@Override
	public ProjectModel createUserProject(UserModel user, ProjectData project) throws DaoException {
		ProjectModel projectModel = projectConverter.dataToModel(project);
		projectModel.setProjectId(null);
		projectModel.setUserId(user.getId());
		entityManager.persist(projectModel);
		return projectModel;
	}

	@Override
	public Optional<ProjectModel> updateUserProjectById(UserModel user, ProjectData project) throws DaoException {
		ProjectModel projectModel = new ProjectModel();
		final Integer projectId = project.getId();
		if (projectId.intValue() == 0) {
			return Optional.ofNullable(null);
		}
		projectModel.setProjectId(projectId);
		entityManager.refresh(projectModel);
		if (projectModel.getUserId() != user.getId()) {
			throw new DaoException("Specified project relates to another user!");
		}
		projectModel = projectConverter.populateDataToModel(projectModel, project);
		entityManager.persist(projectModel);
		return Optional.of(projectModel);
	}

}
