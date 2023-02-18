package tn.cita.app.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.security.jwt")
public record JwtConfigProps(long tokenExpiresAfter, String secretKey) {}




