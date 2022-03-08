package tn.cita.app.dto;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.entity.Credential;

@RequiredArgsConstructor
public final class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private final Credential credential;
	
	@Override
	public String getUsername() {
		return this.credential.getUsername();
	}
	
	@Override
	public String getPassword() {
		return this.credential.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Set.of(new SimpleGrantedAuthority(this.credential.getUserRoleBasedAuthority().getRole()));
	}
	
	@Override
	public boolean isEnabled() {
		return this.credential.getIsEnabled();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return this.credential.getIsAccountNonExpired();
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return this.credential.getIsAccountNonLocked();
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credential.getIsCredentialsNonExpired();
	}
	
	
	
}











