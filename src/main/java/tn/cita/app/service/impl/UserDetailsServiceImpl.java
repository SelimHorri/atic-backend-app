package tn.cita.app.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.UserDetailsImpl;
import tn.cita.app.exception.wrapper.IllegalUserDetailsStateException;
import tn.cita.app.service.CredentialService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final CredentialService credentialService;
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		
		log.info("** UserDetailsServiceImpl; UserDetails; load user by username service...*\n");
		
		final UserDetails userDetails = new UserDetailsImpl(this.credentialService.findByUsername(username.strip()));
		
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













