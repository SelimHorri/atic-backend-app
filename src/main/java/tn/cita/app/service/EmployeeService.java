package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.EmployeeDto;

public interface EmployeeService {
	
	List<EmployeeDto> findAll(final int pageOffset);
	EmployeeDto findById(final Integer id);
	EmployeeDto save(final EmployeeDto employeeDto);
	EmployeeDto update(final EmployeeDto employeeDto);
	boolean deleteById(final Integer id);
	
}












