package tn.cita.app.model.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRoleBasedAuthority {
	
	CUSTOMER("ROLE_CUSTOMER"),
	WORKER("ROLE_WORKER"),
	MANAGER("ROLE_MANAGER"),
	OWNER("ROLE_OWNER"),
	ADMIN("ROLE_ADMIN");
	
	private final String role;
	
}



