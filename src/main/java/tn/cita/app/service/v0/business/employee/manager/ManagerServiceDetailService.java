package tn.cita.app.service.v0.business.employee.manager;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.ServiceDetailDto;

public interface ManagerServiceDetailService {
	
	Page<ServiceDetailDto> getAll();
	ServiceDetailDto getById(final Integer serviceDetailId);
	
}











