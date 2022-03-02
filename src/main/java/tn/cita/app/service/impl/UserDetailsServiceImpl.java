package tn.cita.app.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.UserDetailsImpl;
import tn.cita.app.service.CredentialService;

@Service
// @Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final CredentialService credentialService;
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		return new UserDetailsImpl(this.credentialService.findByUsername(username.strip()));
	}
	
	
	
}













