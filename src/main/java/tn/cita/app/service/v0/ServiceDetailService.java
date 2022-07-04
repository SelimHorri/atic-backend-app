package tn.cita.app.service.v0;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.dto.response.ServiceDetailsReservationContainerResponse;
import tn.cita.app.repository.ServiceDetailRepository;

public interface ServiceDetailService {
	
	ServiceDetailRepository getServiceDetailRepository();
	List<ServiceDetailDto> findAll();
	ServiceDetailDto findById(final Integer id);
	Page<ServiceDetailDto> findAllByIds(final Set<Integer> ids);
	ServiceDetailsReservationContainerResponse getOrderedServiceDetailsByReservationId(final Integer reservationId);
	Page<ServiceDetailDto> findAllByCategoryId(final Integer categoryId);
	Page<ServiceDetailDto> findAllByCategorySaloonId(final Integer saloonId);
	
}













