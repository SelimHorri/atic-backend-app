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
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.service.v0.AuthenticationService;
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
	
	@BeforeEach
	void setUp() throws Exception {
		loginRequest = new LoginRequest("selimhorri", "0000");
	}
	
	@Test
	void givenValidLoginRequest_whenCredentialsAreValid_thenLoginResponseShouldProcessed() {
		
		final var expectedLoginResponse = this.authenticationService.authenticate(this.loginRequest);
		assertThat(expectedLoginResponse).isNotNull();
		assertThat(expectedLoginResponse.username()).isEqualTo(loginRequest.getUsername());
		
		final var validateToken = this.jwtUtil.validateToken(expectedLoginResponse.jwtToken(), 
				this.userDetailsService.loadUserByUsername(expectedLoginResponse.username()));
		assertThat(validateToken).isTrue();
	}
	
	@Test
	void givenInvalidLoginRequest_whenCredentialsAreInvalid_thenShouldThrowIllegalCredentialsException() {
		this.loginRequest = new LoginRequest("selimhorri", "1111");
		final var passwordNotMatchException = assertThrows(PasswordNotMatchException.class, 
				() -> this.authenticationService.authenticate(this.loginRequest));
		assertThat(passwordNotMatchException).isNotNull();
		assertThat(passwordNotMatchException.getMessage()).isEqualTo("Incorrect password");
	}
	
	
	
}

















