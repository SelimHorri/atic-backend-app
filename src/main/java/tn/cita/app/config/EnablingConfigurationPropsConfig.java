package tn.cita.app.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Lazy
@Configuration
@ConfigurationPropertiesScan(basePackages = "tn.cita.app.config.props")
@EnableConfigurationProperties
public class EnablingConfigurationPropsConfig {}




