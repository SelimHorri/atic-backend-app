package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.CustomerDto;

public interface CustomerService {
	
	List<CustomerDto> findAll(final int pageOffset);
	CustomerDto findById(final Integer id);
	boolean deleteById(final Integer id);
	
}













