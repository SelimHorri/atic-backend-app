package tn.cita.app.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

record EnablingBeans() {}



@Lazy
@Configuration
@ConfigurationPropertiesScan(basePackages = "tn.cita.app.model.props")
@EnableConfigurationProperties
class EnablingConfigPropsConfig {}




