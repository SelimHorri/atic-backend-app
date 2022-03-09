package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.EmployeeDto;

public interface EmployeeService {
	
	List<EmployeeDto> findAll(final int pageOffset);
	EmployeeDto findById(final Integer id);
	boolean deleteById(final Integer id);
	
}












