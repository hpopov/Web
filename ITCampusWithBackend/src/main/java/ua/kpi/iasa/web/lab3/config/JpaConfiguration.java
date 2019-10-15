package ua.kpi.iasa.web.lab3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ua.kpi.iasa.web.lab3.dao.base.EnumRepository;
import ua.kpi.iasa.web.lab3.dao.base.GraphRepository;
import ua.kpi.iasa.web.lab3.dao.base.SimpleSaveRepository;

@Configuration
@EnableJpaRepositories(basePackages = "ua.kpi.iasa.web.lab3.dao", basePackageClasses = { EnumRepository.class,
        GraphRepository.class, SimpleSaveRepository.class })
@EnableTransactionManagement
public class JpaConfiguration {

}
