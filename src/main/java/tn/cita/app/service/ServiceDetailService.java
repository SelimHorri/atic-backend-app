package tn.cita.app.service;

import java.util.List;
import java.util.Set;

import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.dto.response.ServiceDetailsReservationContainerResponse;

public interface ServiceDetailService {
	
	ServiceDetailDto findById(final Integer id);
	List<ServiceDetailDto> findAllByIds(final Set<Integer> ids);
	ServiceDetailsReservationContainerResponse getOrderedServiceDetailsByReservationId(final Integer reservationId);
	List<ServiceDetailDto> findAllByCategoryId(final Integer categoryId);
	List<ServiceDetailDto> findAllByCategorySaloonId(final Integer saloonId);
	
}













