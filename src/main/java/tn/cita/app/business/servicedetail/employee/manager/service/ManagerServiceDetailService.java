package tn.cita.app.business.servicedetail.employee.manager.service;

import org.springframework.data.domain.Page;
import tn.cita.app.business.servicedetail.employee.manager.model.ServiceDetailRequest;
import tn.cita.app.model.dto.ServiceDetailDto;

public interface ManagerServiceDetailService {
	
	Page<ServiceDetailDto> fetchAll(final String username);
	ServiceDetailDto fetchById(final Integer serviceDetailId);
	Boolean deleteServiceDetail(final Integer serviceDetailId);
	ServiceDetailDto saveServiceDetail(final ServiceDetailRequest serviceDetailRequest);
	ServiceDetailDto updateServiceDetail(final ServiceDetailRequest serviceDetailRequest);
	
}



