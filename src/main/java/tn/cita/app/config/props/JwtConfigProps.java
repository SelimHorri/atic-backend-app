package tn.cita.app.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Data;

@ConfigurationProperties(prefix = "app.security.jwt")
@ConstructorBinding
@Data
public final class JwtConfigProps {
	
	/**
	 * Period that jwt token expires after.
	 * in millis
	 */
	private final int tokenExpiresAfter;
	
	/**
	 * Jwt secret key.
	 */
	private final String secretKey;
	
}






