package tn.cita.app.service.v0;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.EmployeeDto;

public interface EmployeeService {
	
	Page<EmployeeDto> findAll(final int pageOffset);
	EmployeeDto findById(final Integer id);
	boolean deleteById(final Integer id);
	
}












