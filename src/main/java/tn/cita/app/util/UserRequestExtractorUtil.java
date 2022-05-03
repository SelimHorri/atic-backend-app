package tn.cita.app.util;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.exception.wrapper.UsernameNotMatchException;

@Component
@RequiredArgsConstructor
public class UserRequestExtractorUtil {
	
	private final JwtUtil jwtUtil;
	
	public String extractUsername(final HttpServletRequest httpServletRequest) {
		
		final var username = (Optional.ofNullable(httpServletRequest.getHeader(AppConstant.USERNAME_AUTH_HEADER)).isPresent())? 
				httpServletRequest.getHeader("UsernameAuth") : "";
		
		final var authenticatedUsername = this.jwtUtil
				.extractUsername(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION).substring(7));
		
		if (!username.isBlank())
			if (!authenticatedUsername.equalsIgnoreCase(username))
				throw new UsernameNotMatchException(String
						.format("Authenticated user is not allowed to access another user resources"));
		
		return authenticatedUsername;
	}
	
	
	
}













