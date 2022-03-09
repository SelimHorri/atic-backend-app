package tn.cita.app.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.request.LoginRequest;
import tn.cita.app.dto.response.LoginResponse;
import tn.cita.app.exception.wrapper.IllegalCredentialsException;
import tn.cita.app.service.AuthenticationService;
import tn.cita.app.util.JwtUtil;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	
	@Override
	public LoginResponse authenticate(final LoginRequest loginRequest) {
		
		log.info("** AuthenticationResponse, authenticate user service*\n");
		
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		}
		catch (BadCredentialsException e) {
			throw new IllegalCredentialsException("Bad credentials");
		}
		
		final var userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getUsername().toLowerCase());
		return new LoginResponse(userDetails.getUsername(), this.jwtUtil.generateToken(userDetails));
	}
	
	
	
}















