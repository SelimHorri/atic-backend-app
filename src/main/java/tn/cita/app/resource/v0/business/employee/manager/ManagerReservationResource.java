package tn.cita.app.resource.v0.business.employee.manager;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.request.ReservationAssignWorkerRequest;
import tn.cita.app.dto.response.ManagerReservationResponse;
import tn.cita.app.dto.response.ReservationSubWorkerResponse;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.employee.manager.ManagerReservationService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees/managers/reservations")
@RequiredArgsConstructor
public class ManagerReservationResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerReservationService managerReservationService;
	
	@GetMapping("/paged")
	public ResponseEntity<ApiResponse<ManagerReservationResponse>> getAllReservations(final WebRequest webRequest, 
			@RequestParam final Map<String, String> params) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationService.getAllReservations(this.userRequestExtractorUtil.extractUsername(webRequest), 
						new ClientPageRequest(params))));
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse<ManagerReservationResponse>> getAllReservations(final WebRequest webRequest) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationService.getAllReservations(this.userRequestExtractorUtil.extractUsername(webRequest), null)));
	}
	
	@PutMapping("/cancel/{reservationId}")
	public ResponseEntity<ApiResponse<ReservationDto>> cancelReservation(final WebRequest request, 
			@PathVariable final String reservationId) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationService.cancelReservation(Integer.parseInt(reservationId))));
	}
	
	@GetMapping("/search/{key}")
	public ResponseEntity<ApiResponse<ManagerReservationResponse>> searchAllBySaloonIdLikeKey(final WebRequest webRequest, 
			@PathVariable final String key) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationService.searchAllBySaloonIdLikeKey(this.userRequestExtractorUtil.extractUsername(webRequest), key)));
	}
	
	@GetMapping("/{reservationId}/unassigned")
	public ResponseEntity<ApiResponse<ReservationSubWorkerResponse>> getAllUnassignedSubWorkers(final WebRequest webRequest, 
			@PathVariable final String reservationId) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerReservationService
				.getAllUnassignedSubWorkers(this.userRequestExtractorUtil.extractUsername(webRequest), 
						Integer.parseInt(reservationId))));
	}
	
	@PostMapping("/assign")
	public ResponseEntity<ApiResponse<ReservationSubWorkerResponse>> assignReservationWorkers(final WebRequest webRequest, 
			@RequestBody @Valid final ReservationAssignWorkerRequest reservationAssignWorkerRequest) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerReservationService
				.assignReservationWorkers(this.userRequestExtractorUtil.extractUsername(webRequest), 
						reservationAssignWorkerRequest)));
	}
	
	
	
}


















