package tn.cita.app.service.v0.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.CustomUserDetails;
import tn.cita.app.exception.wrapper.IllegalCredentialsException;
import tn.cita.app.exception.wrapper.IllegalUserDetailsStateException;
import tn.cita.app.mapper.CredentialMapper;
import tn.cita.app.service.v0.CredentialService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final CredentialService credentialService;
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException, BadCredentialsException {
		
		log.info("** Load user by username.. *\n");
		
		final UserDetails userDetails = new CustomUserDetails(this.credentialService.getCredentialRepository()
				.findByUsernameIgnoreCase(username.strip().toLowerCase())
				.map(CredentialMapper::map)
				.orElseThrow(() -> new IllegalCredentialsException("Username is not registered")));
		
		if (!userDetails.isEnabled())
			throw new IllegalUserDetailsStateException(String
					.format("User with username: %s is disabled, checkout your mail to activate", userDetails.getUsername()));
		if (!userDetails.isAccountNonExpired())
			throw new IllegalUserDetailsStateException(String
					.format("User account with username: %s is expired", userDetails.getUsername()));
		if (!userDetails.isAccountNonLocked())
			throw new IllegalUserDetailsStateException(String
					.format("User account with username: %s is locked", userDetails.getUsername()));
		if (!userDetails.isCredentialsNonExpired())
			throw new IllegalUserDetailsStateException(String
					.format("User credentials with username: %s are expired", userDetails.getUsername()));
		
		return userDetails;
	}
	
	
	
}













