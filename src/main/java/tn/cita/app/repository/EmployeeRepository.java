package tn.cita.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Optional<Employee> findByIdentifier(final String identifier);
	Optional<Employee> findByCredentialUsernameIgnoringCase(final String username);
	List<Employee> findAllByManagerId(final Integer managerId);
	
}









