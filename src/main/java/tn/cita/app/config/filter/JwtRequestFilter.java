package tn.cita.app.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.exception.wrapper.AccessTokenExpiredException;
import tn.cita.app.exception.wrapper.UnauthorizedUserException;
import tn.cita.app.util.JwtUtils;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Qualifier("handlerExceptionResolver")
	private final HandlerExceptionResolver exceptionResolver;
	private final UserDetailsService userDetailsService;
	private final JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(final HttpServletRequest request, 
									final HttpServletResponse response, 
									final FilterChain filterChain) throws ServletException, IOException {
		
		log.info("**JwtRequestFilter, once per request, validating and extracting token*\n");
		
		final var authorizationHeader = request.getHeader(AppConstants.AUTHORIZATION_HEADER);
		
		String username = null;
		String jwt = null;
		
		if ( authorizationHeader != null && authorizationHeader.startsWith("Bearer ") ) {
			jwt = authorizationHeader.substring(7);
			
			try {
				username = this.jwtUtils.extractUsername(jwt);
			}
			catch (RuntimeException e) {
				this.exceptionResolver.resolveException(request, response, null, 
						new AccessTokenExpiredException("Account has been expired! "
								+ "Please consider to re-Login"));
				return;
			}
			
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if (!this.jwtUtils.validateToken(jwt, userDetails)) {
				this.exceptionResolver.resolveException(request, response, null, 
						new UnauthorizedUserException("UnauthorizedUserException"));
				return;
			}
			else {
				final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
		log.info("**Jwt request filtered!*\n");
	}
	
}









