package tn.cita.app.service.v0.business.employee.manager.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.service.v0.ServiceDetailService;
import tn.cita.app.service.v0.business.employee.manager.ManagerServiceDetailService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerServiceDetailServiceImpl implements ManagerServiceDetailService {
	
	private final ServiceDetailService serviceDetailService;
	
	@Override
	public Page<ServiceDetailDto> getAll() {
		return new PageImpl<>(this.serviceDetailService.findAll());
	}
	
	@Override
	public ServiceDetailDto getById(final Integer serviceDetailId) {
		return this.serviceDetailService.findById(serviceDetailId);
	}
	
	
	
}















