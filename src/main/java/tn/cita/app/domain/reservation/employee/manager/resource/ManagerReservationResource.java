package tn.cita.app.domain.reservation.employee.manager.resource;

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
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.domain.reservation.employee.manager.model.ManagerReservationResponse;
import tn.cita.app.domain.reservation.employee.manager.model.ReservationAssignWorkerRequest;
import tn.cita.app.domain.reservation.employee.manager.model.ReservationSubWorkerResponse;
import tn.cita.app.domain.reservation.employee.manager.service.ManagerReservationService;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees/managers/reservations")
@Slf4j
@RequiredArgsConstructor
public class ManagerReservationResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerReservationService managerReservationService;
	
	@GetMapping("/paged")
	public ResponseEntity<ApiResponse<ManagerReservationResponse>> fetchAllReservations(final WebRequest webRequest, 
			@RequestParam final Map<String, String> params) {
		log.info("** Fetch all paged reservations by manager.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationService.fetchAllReservations(this.userRequestExtractorUtil.extractUsername(webRequest), 
						ClientPageRequest.from(params))));
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse<ManagerReservationResponse>> fetchAllReservations(final WebRequest webRequest) {
		log.info("** Fetch all reservations by manager.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationService.fetchAllReservations(this.userRequestExtractorUtil.extractUsername(webRequest), null)));
	}
	
	@PutMapping("/cancel/{reservationId}")
	public ResponseEntity<ApiResponse<ReservationDto>> cancelReservation(final WebRequest request, 
			@PathVariable final String reservationId) {
		log.info("** Cancel reservation by manager.. *\n");
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationService.cancelReservation(Integer.parseInt(reservationId))));
	}
	
	@GetMapping("/search/{key}")
	public ResponseEntity<ApiResponse<ManagerReservationResponse>> searchAllBySaloonIdLikeKey(final WebRequest webRequest, 
			@PathVariable final String key) {
		log.info("** Search all reservations by saloonId by manager.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationService.searchAllBySaloonIdLikeKey(this.userRequestExtractorUtil.extractUsername(webRequest), key)));
	}
	
	@GetMapping("/{reservationId}/unassigned")
	public ResponseEntity<ApiResponse<ReservationSubWorkerResponse>> fetchAllUnassignedSubWorkers(final WebRequest webRequest, 
			@PathVariable final String reservationId) {
		log.info("** Fetch all unassigned sub workers by manager.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerReservationService
				.fetchAllUnassignedSubWorkers(this.userRequestExtractorUtil.extractUsername(webRequest), 
						Integer.parseInt(reservationId))));
	}
	
	@PostMapping("/assign")
	public ResponseEntity<ApiResponse<ReservationSubWorkerResponse>> assignReservationWorkers(final WebRequest webRequest, 
			@RequestBody @Valid final ReservationAssignWorkerRequest reservationAssignWorkerRequest) {
		log.info("** Assign workers to a reservation by manager.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerReservationService
				.assignReservationWorkers(this.userRequestExtractorUtil.extractUsername(webRequest), 
						reservationAssignWorkerRequest)));
	}
	
}







