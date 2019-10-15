package ua.kpi.iasa.web.lab3.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "itcampus.file")
@Getter
@Setter
public class FileStorageProperties {
    private String directory;
}
