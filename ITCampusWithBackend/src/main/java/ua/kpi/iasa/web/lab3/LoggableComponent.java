package ua.kpi.iasa.web.lab3;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggableComponent {

	protected Logger log;
	
	@PostConstruct
	public void initialize() {
		log = LoggerFactory.getLogger(getClass());
	}
}
