package ua.kpi.iasa.web.lab3.dao;

import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

import ua.kpi.iasa.web.lab3.model.AvatarModel;

@RepositoryDefinition(domainClass = AvatarModel.class, idClass = String.class)
public interface AvatarDao {

    Optional<AvatarModel> findByUsername(String username);

    AvatarModel save(AvatarModel avatar);
}
