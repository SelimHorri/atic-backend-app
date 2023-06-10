package tn.cita.app.util.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.exception.wrapper.UsernameNotMatchException;
import tn.cita.app.util.JwtUtils;
import tn.cita.app.util.UserRequestExtractorUtil;

@Component
@RequiredArgsConstructor
public class ManagerRequestExtractorUtil implements UserRequestExtractorUtil {
	
	private final JwtUtils jwtUtils;
	
	@Override
	public String extractUsername(final WebRequest request) {

		final var usernameAuthHeader = (request.getHeader(AppConstants.USERNAME_AUTH_HEADER) != null) ? 
				request.getHeader(AppConstants.USERNAME_AUTH_HEADER) : "";
		
		final var authenticatedUsername = this.jwtUtils
				.extractUsername(request.getHeader(AppConstants.AUTHORIZATION_HEADER).substring(7));
		
		if (!usernameAuthHeader.isBlank())
			if (!authenticatedUsername.equalsIgnoreCase(usernameAuthHeader))
				throw new UsernameNotMatchException(String
						.format("Authenticated user is not allowed to access another user resources"));
		
		return authenticatedUsername;
	}
	
}



