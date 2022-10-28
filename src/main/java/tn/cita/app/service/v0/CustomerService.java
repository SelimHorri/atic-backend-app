package tn.cita.app.service.v0;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.request.ClientPageRequest;

public interface CustomerService {
	
	Page<CustomerDto> findAll(final ClientPageRequest clientPageRequest);
	CustomerDto findById(final Integer id);
	CustomerDto findByIdentifier(final String identifier);
	CustomerDto findByCredentialUsername(final String username);
	List<CustomerDto> findAllBySsn(final String ssn);
	boolean deleteById(final Integer id);
	
}













