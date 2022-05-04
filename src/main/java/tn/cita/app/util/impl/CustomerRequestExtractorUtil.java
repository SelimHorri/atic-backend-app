package tn.cita.app.util.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.exception.wrapper.UsernameNotMatchException;
import tn.cita.app.util.JwtUtil;
import tn.cita.app.util.UserRequestExtractorUtil;

@Component
@RequiredArgsConstructor
public class CustomerRequestExtractorUtil implements UserRequestExtractorUtil {
	
	private final JwtUtil jwtUtil;
	
	@Override
	public String extractUsername(final HttpServletRequest request) {
		
		final var username = (Optional.ofNullable(request.getHeader(AppConstant.USERNAME_AUTH_HEADER)).isPresent())? 
				request.getHeader("UsernameAuth") : "";
		
		final var authenticatedUsername = this.jwtUtil
				.extractUsername(request.getHeader(HttpHeaders.AUTHORIZATION).substring(7));
		
		if (!username.isBlank())
			if (!authenticatedUsername.equalsIgnoreCase(username))
				throw new UsernameNotMatchException(String
						.format("Authenticated user is not allowed to access another user resources"));
		
		return authenticatedUsername;
	}
	
	
	
}













