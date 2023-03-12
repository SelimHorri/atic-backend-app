package tn.cita.app.service.v0.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.CategoryNotFoundException;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.exception.wrapper.ServiceDetailNotFoundException;
import tn.cita.app.mapper.OrderedDetailMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.mapper.ServiceDetailMapper;
import tn.cita.app.model.domain.entity.ServiceDetail;
import tn.cita.app.model.dto.OrderedDetailDto;
import tn.cita.app.model.dto.ServiceDetailDto;
import tn.cita.app.model.dto.request.ServiceDetailRequest;
import tn.cita.app.model.dto.response.ServiceDetailsReservationContainerResponse;
import tn.cita.app.repository.CategoryRepository;
import tn.cita.app.repository.OrderedDetailRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.repository.ServiceDetailRepository;
import tn.cita.app.service.v0.ServiceDetailService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ServiceDetailServiceImpl implements ServiceDetailService {
	
	private final ServiceDetailRepository serviceDetailRepository;
	private final OrderedDetailRepository orderedDetailRepository;
	private final CategoryRepository categoryRepository;
	private final ReservationRepository reservationRepository;
	
	@Override
	public List<ServiceDetailDto> findAll() {
		log.info("** Find all service details.. *\n");
		return this.serviceDetailRepository.findAll().stream()
				.map(ServiceDetailMapper::map)
				.distinct()
				.toList();
	}
	
	@Override
	public ServiceDetailDto findById(final Integer id) {
		log.info("** Find service detail by id.. *\n");
		return this.serviceDetailRepository.findById(id)
				.map(ServiceDetailMapper::map)
				.orElseThrow(() -> new ServiceDetailNotFoundException("ServiceDetail not found"));
	}
	
	@Override
	public ServiceDetailDto findByIdentifier(final String identifier) {
		return this.serviceDetailRepository.findByIdentifier(identifier.strip())
				.map(ServiceDetailMapper::map)
				.orElseThrow(() -> new ServiceDetailNotFoundException("ServiceDetail not found"));
	}
	
	@Override
	public Page<ServiceDetailDto> findAllByIds(final Set<Integer> ids) {
		log.info("** Find all service details by ids.. *\n");
		final var list = this.serviceDetailRepository.findAllById(ids).stream()
				.map(ServiceDetailMapper::map)
				.distinct()
				.toList();
		return new PageImpl<>(list);
	}
	
	@Override
	public ServiceDetailsReservationContainerResponse fetchOrderedServiceDetails(final Integer reservationId) {
		
		log.info("** Fetch ordered service details by reservationId.. *\n");
		
		final var orderedDetailDtos = this.orderedDetailRepository
				.findAllByReservationId(reservationId).stream()
					.map(OrderedDetailMapper::map)
					.distinct()
					.collect(Collectors.toList());
		final var ids = orderedDetailDtos.stream()
					.map(OrderedDetailDto::getServiceDetailId)
					.collect(Collectors.toUnmodifiableSet());
		
		return ServiceDetailsReservationContainerResponse.builder()
				.serviceDetailDtos(this.findAllByIds(ids))
				.orderedDetailDtos(new PageImpl<>(orderedDetailDtos))
				.build();
	}
	
	@Override
	public ServiceDetailsReservationContainerResponse fetchOrderedServiceDetails(final String reservationIdentifier) {
		
		log.info("** Fetch ordered service details by reservationIdentifier.. *\n");
		
		final var reservationDto = this.reservationRepository.findByIdentifier(reservationIdentifier)
				.map(ReservationMapper::map)
				.orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
		
		final var orderedDetailDtos = this.orderedDetailRepository
				.findAllByReservationId(reservationDto.getId()).stream()
					.map(OrderedDetailMapper::map)
					.distinct()
					.toList();
		final var ids = orderedDetailDtos.stream()
					.map(OrderedDetailDto::getServiceDetailId)
					.collect(Collectors.toUnmodifiableSet());
		
		return ServiceDetailsReservationContainerResponse.builder()
				.serviceDetailDtos(this.findAllByIds(ids))
				.orderedDetailDtos(new PageImpl<>(orderedDetailDtos))
				.build();
	}
	
	@Override
	public Page<ServiceDetailDto> findAllByCategoryId(final Integer categoryId) {
		log.info("** Find all service details by categoryId.. *\n");
		return new PageImpl<>(this.serviceDetailRepository
				.findAllByCategoryId(categoryId).stream()
					.map(ServiceDetailMapper::map)
					.distinct()
					.toList());
	}
	
	@Override
	public List<ServiceDetailDto> findAllByCategorySaloonId(final Integer saloonId) {
		log.info("** Find all service details by category saloonId.. *\n");
		return this.serviceDetailRepository.findAllByCategorySaloonId(saloonId).stream()
				.map(ServiceDetailMapper::map)
				.distinct()
				.sorted(Comparator.comparing((final ServiceDetailDto sd) -> sd.getCategoryDto().getName())
						.thenComparing(ServiceDetailDto::getName))
				.toList();
	}
	
	@Transactional
	@Override
	public ServiceDetailDto save(final ServiceDetailRequest serviceDetailRequest) {
		
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
	public ServiceDetailDto update(final ServiceDetailRequest serviceDetailRequest) {
		
		log.info("** Update a service detail.. *\n");
		
		final var category = this.categoryRepository.findById(serviceDetailRequest.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
		
		final var serviceDetail = this.serviceDetailRepository
				.findById(serviceDetailRequest.getServiceDetailId())
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








