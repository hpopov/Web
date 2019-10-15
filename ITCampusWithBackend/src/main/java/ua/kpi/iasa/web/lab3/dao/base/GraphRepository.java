package ua.kpi.iasa.web.lab3.dao.base;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface GraphRepository<T, ID> extends Repository<T, ID>{
	
	Optional<T> findOne(Specification<T> spec, String entityGraphName);
	
}
