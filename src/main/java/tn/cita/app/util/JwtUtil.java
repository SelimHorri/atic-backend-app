package tn.cita.app.util;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtUtil {
	
	String extractUsername(final String token);
	Date extractExpiration(final String token);
	<T> T extractClaims(final String token, final Function<Claims, T> claimsResolver);
	Boolean isTokenExpired(final String token);
	String generateToken(final UserDetails userDetails);
	Boolean validateToken(final String token, final UserDetails userDetails);
	
}











