package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.service.EmployeeService;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	@Override
	public List<EmployeeDto> findAll(final int pageOffset) {
		return this.employeeRepository.findAll(PageRequest.of(pageOffset, AppConstant.PAGE_SIZE))
				.stream()
					.map(EmployeeMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	@Override
	public EmployeeDto findById(final Integer id) {
		return this.employeeRepository.findById(id)
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with id: %d not found", id)));
	}
	
	@Override
	public EmployeeDto save(final EmployeeDto employeeDto) {
		return EmployeeMapper.map(this.employeeRepository.save(EmployeeMapper.map(employeeDto)));
	}
	
	@Override
	public EmployeeDto update(final EmployeeDto employeeDto) {
		return EmployeeMapper.map(this.employeeRepository.save(EmployeeMapper.map(employeeDto)));
	}
	
	@Override
	public void deleteById(final Integer id) {
		this.employeeRepository.deleteById(id);
	}
	
	
	
}














