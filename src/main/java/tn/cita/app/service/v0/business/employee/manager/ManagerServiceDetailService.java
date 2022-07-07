package tn.cita.app.service.v0.business.employee.manager;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.dto.request.ServiceDetailRequest;

public interface ManagerServiceDetailService {
	
	Page<ServiceDetailDto> getAll(final String username);
	ServiceDetailDto getById(final Integer serviceDetailId);
	Boolean deleteServiceDetail(final Integer serviceDetailId);
	ServiceDetailDto saveServiceDetail(final ServiceDetailRequest serviceDetailRequest);
	ServiceDetailDto updateServiceDetail(final ServiceDetailRequest serviceDetailRequest);
	
}











