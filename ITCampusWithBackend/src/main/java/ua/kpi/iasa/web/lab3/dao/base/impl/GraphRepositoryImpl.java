package ua.kpi.iasa.web.lab3.dao.base.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import ua.kpi.iasa.web.lab3.dao.base.GraphRepository;

public class GraphRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements GraphRepository<T, ID> {

	private EntityManager em;
	
	public GraphRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}

	@Override
	public Optional<T> findOne(Specification<T> spec, String entityGraphName) {
		TypedQuery<T> query = getQuery(spec, Sort.unsorted());
        query.setHint(EntityGraphType.LOAD.getKey(), em.getEntityGraph(entityGraphName));

		try {
			return Optional.of(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

}
