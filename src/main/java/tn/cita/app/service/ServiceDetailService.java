package tn.cita.app.service;

import java.util.Set;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.dto.response.ServiceDetailsReservationContainerResponse;

public interface ServiceDetailService {
	
	ServiceDetailDto findById(final Integer id);
	Page<ServiceDetailDto> findAllByIds(final Set<Integer> ids);
	ServiceDetailsReservationContainerResponse getOrderedServiceDetailsByReservationId(final Integer reservationId);
	Page<ServiceDetailDto> findAllByCategoryId(final Integer categoryId);
	Page<ServiceDetailDto> findAllByCategorySaloonId(final Integer saloonId);
	
}













