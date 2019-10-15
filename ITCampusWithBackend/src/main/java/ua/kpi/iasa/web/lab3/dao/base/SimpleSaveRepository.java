package ua.kpi.iasa.web.lab3.dao.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface SimpleSaveRepository<T, ID> extends Repository<T, ID> {

	<S extends T> S save(S entity);
}
