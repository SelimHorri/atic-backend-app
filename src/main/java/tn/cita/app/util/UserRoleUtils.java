package tn.cita.app.util;

import tn.cita.app.model.domain.UserRoleBasedAuthority;

public interface UserRoleUtils {
	
	public static boolean isCustomerRole(final String role) {
		return role.equals(UserRoleBasedAuthority.CUSTOMER.name());
	}
	
	public static boolean isWorkerRole(final String role) {
		return role.equals(UserRoleBasedAuthority.WORKER.name());
	}
	
	public static boolean isManagerRole(final String role) {
		return role.equals(UserRoleBasedAuthority.MANAGER.name());
	}
	
	public static boolean isOwnerRole(final String role) {
		return role.equals(UserRoleBasedAuthority.OWNER.name());
	}
	
	public static UserRoleBasedAuthority getUserRoleFrom(final String role) {
		return switch (UserRoleBasedAuthority.valueOf(role)) {
			case CUSTOMER -> UserRoleBasedAuthority.CUSTOMER;
			case WORKER -> UserRoleBasedAuthority.WORKER;
			case MANAGER -> UserRoleBasedAuthority.MANAGER;
			case OWNER -> UserRoleBasedAuthority.OWNER;
			case ADMIN -> UserRoleBasedAuthority.ADMIN;
		};
	}
	
}



