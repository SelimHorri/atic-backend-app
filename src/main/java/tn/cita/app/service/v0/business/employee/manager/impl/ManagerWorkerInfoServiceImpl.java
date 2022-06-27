package tn.cita.app.service.v0.business.employee.manager.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.response.ManagerWorkerInfoResponse;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.business.employee.manager.ManagerWorkerInfoService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerWorkerInfoServiceImpl implements ManagerWorkerInfoService {
	
	private final EmployeeService employeeService;
	
	@Override
	public ManagerWorkerInfoResponse getAllSubWorkers(final String username) {
		final var managerDto = this.employeeService.findByUsername(username);
		return new ManagerWorkerInfoResponse(
				managerDto, 
				new PageImpl<>(this.employeeService.findAllByManagerId(managerDto.getId())));
	}
	
	
	
}
















