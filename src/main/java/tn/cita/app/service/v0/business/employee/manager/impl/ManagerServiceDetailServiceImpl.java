package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.domain.entity.ServiceDetail;
import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.dto.request.ServiceDetailRequest;
import tn.cita.app.exception.wrapper.CategoryNotFoundException;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.ServiceDetailNotFoundException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.mapper.ServiceDetailMapper;
import tn.cita.app.repository.CategoryRepository;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.ServiceDetailRepository;
import tn.cita.app.service.v0.business.employee.manager.ManagerServiceDetailService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ManagerServiceDetailServiceImpl implements ManagerServiceDetailService {
	
	private final EmployeeRepository employeeRepository;
	private final ServiceDetailRepository serviceDetailRepository;
	private final CategoryRepository categoryRepository;
	
	@Override
	public Page<ServiceDetailDto> fetchAll(final String username) {
		
		log.info("** Fetch all service details by manager.. *\n");
		
		final var managerDto = this.employeeRepository.findByCredentialUsernameIgnoringCase(username.strip())
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with username: %s not found", username)));
		
		return new PageImpl<>(this.serviceDetailRepository
				.findAllByCategorySaloonId(managerDto.getSaloonDto().getId()).stream()
					.map(ServiceDetailMapper::map)
					.distinct()
					.sorted(Comparator.comparing((final ServiceDetailDto sd) -> sd.getCategoryDto().getName())
							.thenComparing(ServiceDetailDto::getName))
					.collect(Collectors.toUnmodifiableList()));
	}
	
	@Override
	public ServiceDetailDto fetchById(final Integer serviceDetailId) {
		log.info("** Fetch service detail by id by manager.. *\n");
		return this.serviceDetailRepository.findById(serviceDetailId)
				.map(ServiceDetailMapper::map)
				.orElseThrow(() -> new ServiceDetailNotFoundException("ServiceDetail not found"));
	}
	
	@Transactional
	@Override
	public Boolean deleteServiceDetail(final Integer serviceDetailId) {
		log.info("** Delete service detail by id by manager.. *\n");
		this.serviceDetailRepository.deleteById(serviceDetailId);
		return !this.serviceDetailRepository.existsById(serviceDetailId);
	}
	
	@Transactional
	@Override
	public ServiceDetailDto saveServiceDetail(final ServiceDetailRequest serviceDetailRequest) {
		
		log.info("** Save new service detail.. *\n");
		
		final var category = this.categoryRepository.findById(serviceDetailRequest.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
		
		final var serviceDetail = ServiceDetail.builder()
				.name(serviceDetailRequest.getName().strip().toLowerCase())
				.description((serviceDetailRequest.getDescription() == null 
							|| serviceDetailRequest.getDescription().isBlank()) ?
						null : serviceDetailRequest.getDescription().strip())
				.isAvailable(serviceDetailRequest.getIsAvailable() == null ? true : serviceDetailRequest.getIsAvailable())
				.duration(serviceDetailRequest.getDuration())
				.priceUnit(serviceDetailRequest.getPriceUnit())
				.category(category)
				.build();
		
		return ServiceDetailMapper.map(this.serviceDetailRepository.save(serviceDetail));
	}
	
	@Transactional
	@Override
	public ServiceDetailDto updateServiceDetail(final ServiceDetailRequest serviceDetailRequest) {
		
		log.info("** Update service detail.. *\n");
		
		final var category = this.categoryRepository.findById(serviceDetailRequest.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
		
		final var serviceDetail = this.serviceDetailRepository.findById(serviceDetailRequest.getServiceDetailId())
				.orElseThrow(() -> new ServiceDetailNotFoundException("ServiceDetail not found"));
		
		serviceDetail.setName(serviceDetailRequest.getName().strip().toLowerCase());
		serviceDetail.setDescription((serviceDetailRequest.getDescription() == null 
					|| serviceDetailRequest.getDescription().isBlank()) ?
				null : serviceDetailRequest.getDescription().strip());
		serviceDetail.setIsAvailable(serviceDetailRequest.getIsAvailable() == null ? true : serviceDetailRequest.getIsAvailable());
		serviceDetail.setDuration(serviceDetailRequest.getDuration());
		serviceDetail.setPriceUnit(serviceDetailRequest.getPriceUnit());
		serviceDetail.setCategory(category);
		
		return ServiceDetailMapper.map(this.serviceDetailRepository.save(serviceDetail));
	}
	
	
	
}















