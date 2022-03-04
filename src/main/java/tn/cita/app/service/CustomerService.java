package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.CustomerDto;

public interface CustomerService {
	
	List<CustomerDto> findAll(final int pageOffset);
	CustomerDto findById(final Integer id);
	CustomerDto save(final CustomerDto customerDto);
	CustomerDto update(final CustomerDto customerDto);
	void deleteById(final Integer id);
	
}












