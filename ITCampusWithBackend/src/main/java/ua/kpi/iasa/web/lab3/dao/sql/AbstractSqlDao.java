package ua.kpi.iasa.web.lab3.dao.sql;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import ua.kpi.iasa.web.lab3.LoggableComponent;

public abstract class AbstractSqlDao extends LoggableComponent{

	@Autowired
	protected EntityManager entityManager;
}
