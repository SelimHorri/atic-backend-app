package tn.cita.app.service.v0.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.service.v0.EmployeeService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeRepository getEmployeeRepository() {
		return this.employeeRepository;
	}
	
	@Override
	public Page<EmployeeDto> findAll(final int pageOffset) {
		log.info("** Find all employees offset paged..*\n");
		return this.employeeRepository.findAll(PageRequest.of(pageOffset - 1, AppConstants.PAGE_SIZE))
				.map(EmployeeMapper::map);
	}
	
	@Override
	public EmployeeDto findById(final Integer id) {
		log.info("** Find employee by id..*\n");
		return this.employeeRepository.findById(id)
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
	}
	
	@Override
	public EmployeeDto findByCredentialUsername(final String username) {
		log.info("** Find employee by credential username.. *\n");
		return this.employeeRepository.findByCredentialUsernameIgnoringCase(username)
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with username: %s not found", username)));
	}
	
	@Transactional
	@Override
	public boolean deleteById(final Integer id) {
		log.info("** Delete employee by id..*\n");
		this.employeeRepository.deleteById(id);
		return !this.employeeRepository.existsById(id);
	}
	
	@Override
	public List<EmployeeDto> findAllByManagerId(final Integer managerId) {
		log.info("** Find all employees by managerId.. *\n");
		return this.employeeRepository.findAllByManagerId(managerId).stream()
				.map(EmployeeMapper::map)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}














