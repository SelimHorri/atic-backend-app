package tn.cita.app.service;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
	
	String extractUsername(final String token);
	Date extractExpiration(final String token);
	<T> T extractClaims(final String token, final Function<Claims, T> claimsResolver);
	Boolean isTokenExpired(final String token);
	String generateToken(final UserDetails userDetails);
	Boolean validateToken(final String token, final UserDetails userDetails);
	
}











