package tn.cita.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.model.domain.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Optional<Customer> findByIdentifier(final String identifier);
	Optional<Customer> findByCredentialUsernameIgnoringCase(final String username);
	List<Customer> findAllBySsn(final String ssn);
	
}




