package tn.cita.app.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.userdetails.UserDetailsService;

import tn.cita.app.dto.request.LoginRequest;
import tn.cita.app.exception.wrapper.IllegalCredentialsException;
import tn.cita.app.service.AuthenticationService;
import tn.cita.app.util.JwtUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthenticationServiceImplTest {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationService authenticationService;
	private LoginRequest loginRequest;
	// private LoginResponse loginResponse;
	
	@BeforeEach
	void setUp() throws Exception {
		
		loginRequest = new LoginRequest("selimhorri", "0000");
	}
	
	@Test
	void givenValidLoginRequest_whenCredentialsAreValid_thenLoginResponseShouldProcessed() {
		
		final var expectedLoginResponse = this.authenticationService.login(this.loginRequest);
		assertThat(expectedLoginResponse).isNotNull();
		assertThat(expectedLoginResponse.getUsername()).isEqualTo(loginRequest.getUsername());
		
		final var validateToken = this.jwtUtil.validateToken(expectedLoginResponse.getJwtToken(), 
				this.userDetailsService.loadUserByUsername(loginRequest.getUsername()));
		assertThat(validateToken).isTrue();
	}
	
	@Test
	void givenInvalidLoginRequest_whenCredentialsAreInvalid_thenShouldThrowIllegalCredentialsException() {
		this.loginRequest = new LoginRequest("selimhorri", "1111");
		final var illegalCredentialsException = assertThrows(IllegalCredentialsException.class, () -> this.authenticationService.login(this.loginRequest));
		assertThat(illegalCredentialsException).isNotNull();
		assertThat(illegalCredentialsException.getMessage()).isEqualTo("Bad credentials");
	}
	
	
	
}

















