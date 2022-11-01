package tn.cita.app.service.v0;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.cita.app.model.dto.EmployeeDto;

public interface EmployeeService {
	
	Page<EmployeeDto> findAll(final int pageOffset);
	EmployeeDto findById(final Integer id);
	EmployeeDto findByIdentifier(final String identifier);
	EmployeeDto findByCredentialUsername(final String username);
	List<EmployeeDto> findAllBySsn(final String ssn);
	boolean deleteById(final Integer id);
	List<EmployeeDto> findAllByManagerId(final Integer managerId);
	
}












