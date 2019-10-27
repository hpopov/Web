package ua.kpi.iasa.web.lab3.dao;

import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

import ua.kpi.iasa.web.lab3.model.TemporaryFileModel;

@RepositoryDefinition(domainClass = TemporaryFileModel.class, idClass = Integer.class)
public interface TemporaryFileDao {

    Optional<TemporaryFileModel> findByUsername(String username);

    Optional<TemporaryFileModel> findById(Integer id);

    TemporaryFileModel save(TemporaryFileModel temporaryFile);

    void delete(TemporaryFileModel model);
}
