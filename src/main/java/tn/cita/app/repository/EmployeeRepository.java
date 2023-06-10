package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.cita.app.model.domain.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Optional<Employee> findByIdentifier(final String identifier);
	Optional<Employee> findByCredentialUsernameIgnoringCase(final String username);
	List<Employee> findAllBySsn(final String ssn);
	List<Employee> findAllByManagerId(final Integer managerId);
	
}



