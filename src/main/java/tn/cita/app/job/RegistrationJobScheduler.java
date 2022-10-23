package tn.cita.app.job;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.domain.UserRoleBasedAuthority;
import tn.cita.app.repository.CredentialRepository;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.EmployeeRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationJobScheduler {
	
	private final CredentialRepository credentialRepository;
	private final CustomerRepository customerRepository;
	private final EmployeeRepository employeeRepository;
	
	/**
	 * Takes all disabled customers registered in the system, and delete them all due to unconfirmed state..
	 * Executed each weak (each 7 days)
	 */
	@Scheduled(fixedDelay = 7, timeUnit = TimeUnit.DAYS)
	void scheduleCustomerUnconfirmedRegistration() {
		
		log.info("** Schedule Unconfirmed customers after a while from registration.. *\n");
		
		final var counted = this.credentialRepository
				.findAllByIsEnabledAndUserRoleBasedAuthority(false, UserRoleBasedAuthority.CUSTOMER).stream()
					.filter(c -> Instant.now().minus(1, ChronoUnit.WEEKS).isBefore(c.getCreatedAt()))
					.distinct()
					.map(c -> {
						var id = c.getCustomer().getId();
						this.customerRepository.deleteById(id);
						return id;
					})
					.peek(id -> log.info("** Customer with id: {} has been deleted **\n", id))
					.count();
		log.info("** {} customers have been deleted due to unconfirmed accounts **\n", counted);
	}
	
	/**
	 * Takes all disabled employees registered in the system, and delete them all due to unconfirmed state..
	 * Executed each weak (each 7 days)
	 */
	@Scheduled(fixedDelay = 7, timeUnit = TimeUnit.DAYS)
	void scheduleEmployeeUnconfirmedRegistration() {
		
		log.info("** Schedule Unconfirmed employees after a while from registration.. *\n");
		
		var counted = 0L;
		
		for (var role : UserRoleBasedAuthority.values()) {
			counted += switch (role) {
				case WORKER, MANAGER, OWNER -> this.processEmployeeRemoval(role);
				default -> throw new RuntimeException("Incorrect requested role");
			};
		}
		
		log.info("** {} employees have been deleted due to unconfirmed accounts **\n", counted);
	}
	
	private long processEmployeeRemoval(@NonNull final UserRoleBasedAuthority role) {
		return this.credentialRepository
				.findAllByIsEnabledAndUserRoleBasedAuthority(false, role).stream()
					.filter(c -> Instant.now().minus(1, ChronoUnit.WEEKS).isBefore(c.getCreatedAt()))
					.distinct()
					.map(c -> {
						var id = c.getEmployee().getId();
						this.employeeRepository.deleteById(id);
						return id;
					})
					.peek(id -> log.info("** {} with id: {} has been deleted **\n", role.name(), id))
					.count();
	}
	
	
	
}










