package tn.cita.app.service;

import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

import java.util.List;

public interface CustomerService {
	
	Page<CustomerDto> findAll(final ClientPageRequest clientPageRequest);
	CustomerDto findById(final Integer id);
	CustomerDto findByIdentifier(final String identifier);
	CustomerDto findByCredentialUsername(final String username);
	List<CustomerDto> findAllBySsn(final String ssn);
	boolean deleteById(final Integer id);
	
}



