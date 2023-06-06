package tn.cita.app.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.service.CustomerService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Override
	public Page<CustomerDto> findAll(final ClientPageRequest clientPageRequest) {
		log.info("** Find All paged customers.. *\n");
		return this.customerRepository.findAll(PageRequest
					.of(clientPageRequest.getOffset() - 1, clientPageRequest.getSize()))
				.map(CustomerMapper::toDto);
	}
	
	@Override
	public CustomerDto findById(final Integer id) {
		log.info("** Find customer by id.. *\n");
		return this.customerRepository.findById(id)
				.map(CustomerMapper::toDto)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
	}
	
	@Override
	public CustomerDto findByIdentifier(final String identifier) {
		log.info("** Find customer by identifier.. *\n");
		return this.customerRepository.findByIdentifier(identifier.strip())
				.map(CustomerMapper::toDto)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
	}
	
	@Override
	public CustomerDto findByCredentialUsername(final String username) {
		log.info("** Find customer by credential username.. *\n");
		return this.customerRepository.findByCredentialUsernameIgnoringCase(username)
				.map(CustomerMapper::toDto)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with username: %s not found".formatted(username)));
	}
	
	@Override
	public List<CustomerDto> findAllBySsn(final String ssn) {
		log.info("** Find customer(s) by ssn.. *\n");
		return this.customerRepository.findAllBySsn(ssn.strip()).stream()
				.map(CustomerMapper::toDto)
				.distinct()
				.toList();
	}
	
	@Transactional
	@Override
	public boolean deleteById(final Integer id) {
		log.info("** Delete customer by id..*\n");
		this.customerRepository.deleteById(id);
		return !this.customerRepository.existsById(id);
	}
	
}




