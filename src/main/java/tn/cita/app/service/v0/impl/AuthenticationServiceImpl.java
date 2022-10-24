package tn.cita.app.service.v0.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.request.LoginRequest;
import tn.cita.app.dto.response.LoginResponse;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.service.v0.AuthenticationService;
import tn.cita.app.util.JwtUtil;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public LoginResponse authenticate(final LoginRequest loginRequest) {
		
		log.info("** Authenticate user.. *\n");
		
		final var userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getUsername());
		
		if (userDetails == null || !this.passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword()))
			throw new PasswordNotMatchException("Incorrect password");
		
		final var authentication = this.authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), loginRequest.getPassword()));
		
		return new LoginResponse(authentication.getName(), this.jwtUtil.generateToken(userDetails));
	}
	
	
	
}















