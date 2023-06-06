package tn.cita.app.model.props;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.client")
public record ClientConfigProps(List<String> domains) {}


