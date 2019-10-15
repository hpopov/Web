package ua.kpi.iasa.web.lab3.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import ua.kpi.iasa.web.lab3.config.properties.FileStorageProperties;
import ua.kpi.iasa.web.lab3.config.properties.UserProperties;

@Configuration
@EnableConfigurationProperties({ FileStorageProperties.class, UserProperties.class })
public class PropertiesConfiguration {

}
