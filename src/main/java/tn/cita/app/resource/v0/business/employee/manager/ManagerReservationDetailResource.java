package tn.cita.app.resource.v0.business.employee.manager;

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
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.request.ReservationAssignWorkerRequest;
import tn.cita.app.dto.response.ReservationBeginEndTask;
import tn.cita.app.dto.response.ReservationDetailResponse;
import tn.cita.app.dto.response.ReservationSubWorkerResponse;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.employee.manager.ManagerReservationDetailService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees/managers/reservations/details")
@RequiredArgsConstructor
public class ManagerReservationDetailResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerReservationDetailService managerReservationDetailService;
	
	@GetMapping("/{reservationId}")
	public ResponseEntity<ApiResponse<ReservationDetailResponse>> getReservationDetails(final WebRequest request,
			@PathVariable final String reservationId) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationDetailService.getReservationDetails(Integer.parseInt(reservationId))));
	}
	
	@GetMapping("/{reservationId}/tasks/info/beginEnd")
	public ResponseEntity<ApiResponse<ReservationBeginEndTask>> getBeginEndTask(final WebRequest webRequest, 
			@PathVariable final String reservationId) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationDetailService.getBeginEndTask(Integer.parseInt(reservationId))));
	}
	
	@GetMapping("/{reservationId}/tasks/unassigned")
	public ResponseEntity<ApiResponse<ReservationSubWorkerResponse>> getAllUnassignedSubWorkers(final WebRequest webRequest, 
			@PathVariable final String reservationId) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerReservationDetailService
				.getAllUnassignedSubWorkers(this.userRequestExtractorUtil.extractUsername(webRequest), 
						Integer.parseInt(reservationId))));
	}
	
	@PostMapping("/tasks/assign")
	public ResponseEntity<ApiResponse<ReservationSubWorkerResponse>> assignReservationWorkers(final WebRequest webRequest, 
			@RequestBody @Valid final ReservationAssignWorkerRequest reservationAssignWorkerRequest) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerReservationDetailService
				.assignReservationWorkers(this.userRequestExtractorUtil.extractUsername(webRequest), 
						reservationAssignWorkerRequest)));
	}
	
	
	
}















