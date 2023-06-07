package tn.cita.app.business.reservation.employee.worker.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import tn.cita.app.business.reservation.employee.worker.service.WorkerReservationService;
import tn.cita.app.model.dto.TaskDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.util.UserRequestExtractorUtil;

import java.util.Map;

@RestController
@RequestMapping("${app.api-version}" + "/employees/workers/reservations")
@Slf4j
@RequiredArgsConstructor
public class WorkerReservationResource {
	
	@Qualifier("workerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final WorkerReservationService workerReservationService;
	
	@GetMapping("/paged")
	public ResponseEntity<ApiResponse<Page<TaskDto>>> fetchAllReservations(final WebRequest webRequest, 
			@RequestParam final Map<String, String> params) {
		log.info("** Fetch all paged worker reservations.. *\n");
		final var reservations = this.workerReservationService.fetchAllReservations(
				this.userRequestExtractorUtil.extractUsername(webRequest), ClientPageRequest.from(params));
		return ResponseEntity.ok(new ApiResponse<>(reservations.getSize(), HttpStatus.OK, true, reservations));
	}
	
	@GetMapping({"", "/all"})
	public ResponseEntity<ApiResponse<Page<TaskDto>>> fetchAllReservations(final WebRequest webRequest) {
		log.info("** Fetch all worker reservations.. *\n");
		final var reservations = this.workerReservationService
				.fetchAllReservations(this.userRequestExtractorUtil.extractUsername(webRequest));
		return ResponseEntity.ok(new ApiResponse<>(reservations.getSize(), HttpStatus.OK, true, reservations));
	}
	
	@GetMapping("/search/{key}")
	public ResponseEntity<ApiResponse<Page<TaskDto>>> searchAllReservationsLikeKey(
			final WebRequest webRequest, @PathVariable final String key) {
		log.info("** Search all worker reservations like key.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(0, HttpStatus.OK, true, 
				this.workerReservationService.searchAllLikeKey(
						this.userRequestExtractorUtil.extractUsername(webRequest), key)));
	}
	
}




