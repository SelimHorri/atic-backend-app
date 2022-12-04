package tn.cita.app.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	@Value("${app.security.jwt.secret-key}")
	private String secretKey;
	
	@Value("${app.security.jwt.token-expires-after:36000000}")
	private Integer jwtTokenExpiresAfter;
	
	public String extractUsername(final String token) {
		return this.extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExpiration(final String token) {
		return this.extractClaims(token, Claims::getExpiration);
	}
	
	public <T> T extractClaims(final String token, Function<Claims, T> claimsResolver) {
		final Claims claims = this.extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(final String token) {
		return Jwts.parserBuilder()
				.setSigningKey(this.secretKey.getBytes(StandardCharsets.UTF_8))
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public Boolean isTokenExpired(final String token) {
		return this.extractExpiration(token).before(new Date());
	}
	
	public String generateToken(final UserDetails userDetails) {
		final Map<String, Object> claims = new HashMap<>();
		return this.createToken(claims, userDetails.getUsername());
	}
	
	private String createToken(final Map<String, Object> claims, final String subject) throws NumberFormatException {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + this.jwtTokenExpiresAfter.intValue()))
				.signWith(Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
				.compact();
	}
	
	public Boolean validateToken(final String token, final UserDetails userDetails) {
		final String username = this.extractUsername(token);
		return username.equals(userDetails.getUsername()) && !this.isTokenExpired(token);
	}
	
	
	
}











