package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.response.CustomerProfileResponse;

public interface CustomerService {
	
	List<CustomerDto> findAll(final int pageOffset);
	CustomerDto findById(final Integer id);
	CustomerDto findByCredentialUsernameIgnoringCase(final String username);
	boolean deleteById(final Integer id);
	CustomerProfileResponse getCustomerProfileByUsername(final String username);
	
}













