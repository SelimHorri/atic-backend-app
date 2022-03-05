package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.service.CustomerService;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<CustomerDto> findAll(final int pageOffset) {
		return this.customerRepository.findAll(PageRequest.of(pageOffset, AppConstant.PAGE_SIZE))
				.stream()
					.map(CustomerMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	@Transactional(readOnly = true)
	@Override
	public CustomerDto findById(final Integer id) {
		return this.customerRepository.findById(id)
				.map(CustomerMapper::map)
				.orElseThrow(() -> new CustomerNotFoundException(String
						.format("Customer with id: %d not found", id)));
	}
	
	@Override
	public CustomerDto save(final CustomerDto customerDto) {
		return CustomerMapper.map(this.customerRepository.save(CustomerMapper.map(customerDto)));
	}
	
	@Override
	public CustomerDto update(final CustomerDto customerDto) {
		return CustomerMapper.map(this.customerRepository.save(CustomerMapper.map(customerDto)));
	}
	
	@Override
	public boolean deleteById(final Integer id) {
		this.customerRepository.deleteById(id);
		return !this.customerRepository.existsById(id);
	}
	
	
	
}














