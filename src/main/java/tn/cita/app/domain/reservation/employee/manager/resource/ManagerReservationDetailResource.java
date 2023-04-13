package tn.cita.app.domain.reservation.employee.manager.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.domain.reservation.employee.manager.model.ReservationAssignWorkerRequest;
import tn.cita.app.domain.reservation.employee.manager.model.ReservationSubWorkerResponse;
import tn.cita.app.domain.reservation.employee.manager.service.ManagerReservationDetailService;
import tn.cita.app.model.dto.response.ReservationBeginEndTask;
import tn.cita.app.model.dto.response.ReservationDetailResponse;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees/managers/reservations/details")
@Slf4j
@RequiredArgsConstructor
public class ManagerReservationDetailResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerReservationDetailService managerReservationDetailService;
	
	@GetMapping("/{reservationId}")
	public ResponseEntity<ApiResponse<ReservationDetailResponse>> fetchReservationDetails(final WebRequest request,
			@PathVariable final String reservationId) {
		log.info("** Fetch manager reservation details.. *\n");
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationDetailService.fetchReservationDetails(Integer.parseInt(reservationId))));
	}
	
	@GetMapping("/{reservationId}/tasks/info/beginEnd")
	public ResponseEntity<ApiResponse<ReservationBeginEndTask>> fetchBeginEndTask(final WebRequest webRequest, 
			@PathVariable final String reservationId) {
		log.info("** Fetch manager begin and end task.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationDetailService.fetchBeginEndTask(Integer.parseInt(reservationId))));
	}
	
	@GetMapping("/{reservationId}/tasks/unassigned")
	public ResponseEntity<ApiResponse<ReservationSubWorkerResponse>> fetchAllUnassignedSubWorkers(final WebRequest webRequest, 
			@PathVariable final String reservationId) {
		log.info("** Fetch all manager unassigned sub workers.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerReservationDetailService
				.fetchAllUnassignedSubWorkers(this.userRequestExtractorUtil.extractUsername(webRequest), 
						Integer.parseInt(reservationId))));
	}
	
	@PostMapping("/tasks/assign")
	public ResponseEntity<ApiResponse<ReservationSubWorkerResponse>> assignReservationWorkers(final WebRequest webRequest, 
			@RequestBody @Valid final ReservationAssignWorkerRequest reservationAssignWorkerRequest) {
		log.info("** Assign sub workers to a reservation by manager.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerReservationDetailService
				.assignReservationWorkers(this.userRequestExtractorUtil.extractUsername(webRequest), 
						reservationAssignWorkerRequest)));
	}
	
}







