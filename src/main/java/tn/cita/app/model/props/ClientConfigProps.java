package tn.cita.app.model.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app.client")
public record ClientConfigProps(List<String> domains) {}


