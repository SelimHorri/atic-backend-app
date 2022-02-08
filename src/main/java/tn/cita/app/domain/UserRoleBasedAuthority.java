package tn.cita.app.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRoleBasedAuthority {
	
	CUSTOMER("ROLE_CUSTOMER"),
	EMPLOYEE("ROLE_EMPLOYEE"),
	MANAGER("ROLE_MANAGER");
	
	private final String role;
	
}
