package tn.cita.app.service.v0.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstant;
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
	public Page<EmployeeDto> findAll(final int pageOffset) {
		log.info("** EmployeeServiceImpl; EmployeeDto; find all with pageOffset service...*\n");
		return this.employeeRepository.findAll(PageRequest.of(pageOffset - 1, AppConstant.PAGE_SIZE))
				.map(EmployeeMapper::map);
	}
	
	@Override
	public EmployeeDto findById(final Integer id) {
		log.info("** EmployeeServiceImpl; EmployeeDto; find user by id service...*\n");
		return this.employeeRepository.findById(id)
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with id: %d not found", id)));
	}
	
	@Transactional
	@Override
	public boolean deleteById(final Integer id) {
		log.info("** EmployeeServiceImpl; boolean; delete user by id service...*\n");
		this.employeeRepository.deleteById(id);
		return !this.employeeRepository.existsById(id);
	}
	
	
	
}














