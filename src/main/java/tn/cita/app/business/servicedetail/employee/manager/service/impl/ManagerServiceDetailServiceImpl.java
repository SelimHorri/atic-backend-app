package tn.cita.app.business.servicedetail.employee.manager.service.impl;

import java.util.Comparator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.business.servicedetail.employee.manager.model.ServiceDetailRequest;
import tn.cita.app.business.servicedetail.employee.manager.service.ManagerServiceDetailService;
import tn.cita.app.exception.wrapper.CategoryNotFoundException;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.ServiceDetailNotFoundException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.mapper.ServiceDetailMapper;
import tn.cita.app.model.domain.entity.ServiceDetail;
import tn.cita.app.model.dto.ServiceDetailDto;
import tn.cita.app.repository.CategoryRepository;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.ServiceDetailRepository;
import tn.cita.app.util.StringWrapperUtils;

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
		
		final var managerDto = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(username.strip())
				.map(EmployeeMapper::toDto)
				.orElseThrow(() -> 
						new EmployeeNotFoundException("Employee with username: %s not found".formatted(username)));
		
		return new PageImpl<>(this.serviceDetailRepository
				.findAllByCategorySaloonId(managerDto.getSaloonDto().getId()).stream()
					.map(ServiceDetailMapper::toDto)
					.distinct()
					.sorted(Comparator.comparing((final ServiceDetailDto sd) -> sd.getCategoryDto().getName())
							.thenComparing(ServiceDetailDto::getName))
					.toList());
	}
	
	@Override
	public ServiceDetailDto fetchById(final Integer serviceDetailId) {
		log.info("** Fetch service detail by id by manager.. *\n");
		return this.serviceDetailRepository.findById(serviceDetailId)
				.map(ServiceDetailMapper::toDto)
				.orElseThrow(ServiceDetailNotFoundException::new);
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
				.description(StringWrapperUtils
						.trimIfBlank(serviceDetailRequest.getDescription()))
					.isAvailable(serviceDetailRequest.getIsAvailable() == null ? true : serviceDetailRequest.getIsAvailable())
				.duration(serviceDetailRequest.getDuration())
				.priceUnit(serviceDetailRequest.getPriceUnit())
				.category(category)
				.build();
		
		return ServiceDetailMapper.toDto(this.serviceDetailRepository.save(serviceDetail));
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
		serviceDetail.setDescription(StringWrapperUtils
				.trimIfBlank(serviceDetailRequest.getDescription()));
		serviceDetail.setIsAvailable(serviceDetailRequest.getIsAvailable() == null ? true : serviceDetailRequest.getIsAvailable());
		serviceDetail.setDuration(serviceDetailRequest.getDuration());
		serviceDetail.setPriceUnit(serviceDetailRequest.getPriceUnit());
		serviceDetail.setCategory(category);
		
		return ServiceDetailMapper.toDto(this.serviceDetailRepository.save(serviceDetail));
	}
	
}









