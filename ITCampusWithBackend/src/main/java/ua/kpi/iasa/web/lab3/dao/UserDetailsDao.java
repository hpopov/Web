package ua.kpi.iasa.web.lab3.dao;

import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

import ua.kpi.iasa.web.lab3.model.UserDetailsModel;

@RepositoryDefinition(domainClass = UserDetailsModel.class, idClass = String.class)
public interface UserDetailsDao {
    
	public Optional<UserDetailsModel> findByUsername(String username);
}
