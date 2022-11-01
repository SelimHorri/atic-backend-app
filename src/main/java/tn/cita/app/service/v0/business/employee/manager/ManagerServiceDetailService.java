package tn.cita.app.service.v0.business.employee.manager;

import org.springframework.data.domain.Page;

import tn.cita.app.model.dto.ServiceDetailDto;
import tn.cita.app.model.dto.request.ServiceDetailRequest;

public interface ManagerServiceDetailService {
	
	Page<ServiceDetailDto> fetchAll(final String username);
	ServiceDetailDto fetchById(final Integer serviceDetailId);
	Boolean deleteServiceDetail(final Integer serviceDetailId);
	ServiceDetailDto saveServiceDetail(final ServiceDetailRequest serviceDetailRequest);
	ServiceDetailDto updateServiceDetail(final ServiceDetailRequest serviceDetailRequest);
	
}











