package tn.cita.app.service.v0.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.service.v0.CustomerService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Override
	public CustomerRepository getCustomerRepository() {
		return this.customerRepository;
	}
	
	@Override
	public Page<CustomerDto> findAll(final ClientPageRequest clientPageRequest) {
		log.info("** Find All paged customers.. *\n");
		return this.customerRepository.findAll(PageRequest
					.of(clientPageRequest.getOffset() - 1, clientPageRequest.getSize()))
				.map(CustomerMapper::map);
	}
	
	@Override
	public CustomerDto findById(final Integer id) {
		log.info("** Find customer by id.. *\n");
		return this.customerRepository.findById(id)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException(String
						.format("Customer with id: %d not found", id)));
	}
	
	@Transactional
	@Override
	public boolean deleteById(final Integer id) {
		log.info("** Delete customer by id..*\n");
		this.customerRepository.deleteById(id);
		return !this.customerRepository.existsById(id);
	}
	
	@Override
	public CustomerDto findByCredentialUsername(final String username) {
		log.info("** Find customer by credential username.. *\n");
		return this.customerRepository.findByCredentialUsernameIgnoringCase(username)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException(String
						.format("Customer with username: %s not found", username)));
	}
	
	
	
}














