package tn.cita.app.resource.v0.business.employee.worker;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.v0.business.employee.worker.WorkerReservationService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/workers/reservations")
@RequiredArgsConstructor
public class WorkerReservationResource {
	
	@Qualifier("workerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final WorkerReservationService workerReservationService;
	
	@GetMapping
	public ResponseEntity<ApiPayloadResponse<Page<TaskDto>>> getAllReservations(final WebRequest webRequest, 
			@RequestParam final Map<String, String> params) {
		final var reservations = this.workerReservationService.getAllReservations(this.userRequestExtractorUtil
				.extractUsername(webRequest), null);
		return ResponseEntity.ok(new ApiPayloadResponse<>(reservations.getSize(), HttpStatus.OK, true, reservations));
	}
	
	
	
}














