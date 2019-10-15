package ua.kpi.iasa.web.lab3.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "itcampus.user")
@Getter
@Setter
public class UserProperties {
    private String defaultAvatarPath;
}
