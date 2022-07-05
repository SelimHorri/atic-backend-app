package tn.cita.app.service.v0;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.repository.CustomerRepository;

public interface CustomerService {
	
	CustomerRepository getCustomerRepository();
	Page<CustomerDto> findAll(final ClientPageRequest clientPageRequest);
	CustomerDto findById(final Integer id);
	CustomerDto findByCredentialUsername(final String username);
	boolean deleteById(final Integer id);
	
}













