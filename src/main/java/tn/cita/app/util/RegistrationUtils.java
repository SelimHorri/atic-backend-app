package tn.cita.app.util;

import tn.cita.app.domain.UserRoleBasedAuthority;

public interface RegistrationUtils {
	
	public static boolean isCustomerRole(final String role) {
		return role.equals(UserRoleBasedAuthority.CUSTOMER.name());
	}
	
	public static boolean isWorkerRole(final String role) {
		return role.equals(UserRoleBasedAuthority.EMPLOYEE.name());
	}
	
	public static boolean isManagerRole(final String role) {
		return role.equals(UserRoleBasedAuthority.MANAGER.name());
	}
	
	public static boolean isOwnerRole(final String role) {
		return role.equals(UserRoleBasedAuthority.OWNER.name());
	}
	
	public static UserRoleBasedAuthority checkUserRoleBasedAuthority(final String role) {
		
		final var CUSTOMER = UserRoleBasedAuthority.CUSTOMER;
		final var EMPLOYEE = UserRoleBasedAuthority.EMPLOYEE;
		final var MANAGER = UserRoleBasedAuthority.MANAGER;
		final var OWNER = UserRoleBasedAuthority.OWNER;
		
		if (role.equalsIgnoreCase(CUSTOMER.name()))
			return CUSTOMER;
		else if (role.equalsIgnoreCase(EMPLOYEE.name()))
			return EMPLOYEE;
		else if (role.equalsIgnoreCase(MANAGER.name()))
			return MANAGER;
		else if (role.equalsIgnoreCase(OWNER.name()))
			return OWNER;
		
		return null;
	}
	
	
	
}














