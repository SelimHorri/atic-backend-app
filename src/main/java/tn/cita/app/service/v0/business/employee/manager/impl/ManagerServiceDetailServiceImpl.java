package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.dto.request.ServiceDetailRequest;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.ServiceDetailService;
import tn.cita.app.service.v0.business.employee.manager.ManagerServiceDetailService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerServiceDetailServiceImpl implements ManagerServiceDetailService {
	
	private final EmployeeService employeeService;
	private final ServiceDetailService serviceDetailService;
	
	@Override
	public Page<ServiceDetailDto> getAll(final String username) {
		return new PageImpl<>(this.serviceDetailService
				.findAllByCategorySaloonId(this.employeeService
						.findByCredentialUsername(username).getSaloonDto().getId()).stream()
					.distinct()
					.sorted(Comparator.comparing((final ServiceDetailDto sd) -> sd.getCategoryDto().getName())
							.thenComparing(ServiceDetailDto::getName))
					.collect(Collectors.toUnmodifiableList()));
	}
	
	@Override
	public ServiceDetailDto getById(final Integer serviceDetailId) {
		return this.serviceDetailService.findById(serviceDetailId);
	}
	
	@Transactional
	@Override
	public Boolean deleteServiceDetail(final Integer serviceDetailId) {
		this.serviceDetailService.getServiceDetailRepository().deleteById(serviceDetailId);
		return !this.serviceDetailService.getServiceDetailRepository().existsById(serviceDetailId);
	}
	
	@Transactional
	@Override
	public ServiceDetailDto saveServiceDetail(final ServiceDetailRequest serviceDetailRequest) {
		return this.serviceDetailService.save(serviceDetailRequest);
	}
	
	@Transactional
	@Override
	public ServiceDetailDto updateServiceDetail(final ServiceDetailRequest serviceDetailRequest) {
		return this.serviceDetailService.update(serviceDetailRequest);
	}
	
	
	
}















