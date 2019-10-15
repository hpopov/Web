package ua.kpi.iasa.web.lab3.dao.base.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.dao.base.SimpleSaveRepository;

@Repository
public class SimpleSaveRepositoryImpl<T, ID> implements SimpleSaveRepository<T, ID>{

	@Autowired
	private EntityManager em;
	@Override
	public <S extends T> S save(S entity) {
		em.persist(entity);
		return entity;
	}


}
