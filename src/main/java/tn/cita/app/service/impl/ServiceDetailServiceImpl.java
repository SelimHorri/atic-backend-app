package tn.cita.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.exception.wrapper.ServiceDetailNotFoundException;
import tn.cita.app.mapper.ServiceDetailMapper;
import tn.cita.app.repository.ServiceDetailRepository;
import tn.cita.app.service.ServiceDetailService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ServiceDetailServiceImpl implements ServiceDetailService {
	
	private final ServiceDetailRepository serviceDetailRepository;
	
	@Override
	public ServiceDetailDto findById(final Integer id) {
		return this.serviceDetailRepository.findById(id)
				.map(ServiceDetailMapper::map)
				.orElseThrow(() -> new ServiceDetailNotFoundException(String
						.format("ServiceDetail with id: %d not found", id)));
	}
	
	
	
}
















