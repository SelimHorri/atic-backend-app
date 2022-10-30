package tn.cita.app.service.v0;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.dto.request.ServiceDetailRequest;
import tn.cita.app.dto.response.ServiceDetailsReservationContainerResponse;

public interface ServiceDetailService {
	
	List<ServiceDetailDto> findAll();
	ServiceDetailDto findById(final Integer id);
	ServiceDetailDto findByIdentifier(final String identifier);
	Page<ServiceDetailDto> findAllByIds(final Set<Integer> ids);
	ServiceDetailsReservationContainerResponse fetchOrderedServiceDetails(final Integer reservationId);
	ServiceDetailsReservationContainerResponse fetchOrderedServiceDetails(final String reservationIdentifier);
	Page<ServiceDetailDto> findAllByCategoryId(final Integer categoryId);
	List<ServiceDetailDto> findAllByCategorySaloonId(final Integer saloonId);
	ServiceDetailDto save(final ServiceDetailRequest serviceDetailRequest);
	ServiceDetailDto update(final ServiceDetailRequest serviceDetailRequest);
	
}













