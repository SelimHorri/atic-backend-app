package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.Optional;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.ManagerReservationResponse;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.business.employee.manager.ManagerReservationService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerReservationServiceImpl implements ManagerReservationService {
	
	private final EmployeeService employeeService;
	private final ReservationService reservationService;
	
	@Override
	public ManagerReservationResponse getAllReservations(final String username, final ClientPageRequest clientPageRequest) {
		final var managerDto = this.employeeService.findByUsername(username);
		if (Optional.ofNullable(clientPageRequest).isPresent())
			return new ManagerReservationResponse(
					managerDto, 
					this.reservationService.findAllBySaloonId(managerDto.getSaloonDto().getId(), clientPageRequest));
		else
			return new ManagerReservationResponse(
					managerDto, 
					new PageImpl<>(this.reservationService.findAllBySaloonId(managerDto.getSaloonDto().getId())));
	}
	
	
	
}

















