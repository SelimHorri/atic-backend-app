package tn.cita.app.resource.v0.business.employee.worker;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.TaskBeginEndRequest;
import tn.cita.app.dto.request.TaskUpdateDescriptionRequest;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.employee.worker.WorkerReservationTaskService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees/workers/reservations/tasks")
@Slf4j
@RequiredArgsConstructor
public class WorkerReservationTaskResource {
	
	@Qualifier("workerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final WorkerReservationTaskService workerReservationTaskService;
	
	@GetMapping("/{reservationId}")
	public ResponseEntity<ApiResponse<TaskDto>> fetchAssignedTask(final WebRequest webRequest, @PathVariable final String reservationId) {
		log.info("** Fetch worker assigned task.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerReservationTaskService.fetchAssignedTask(this.userRequestExtractorUtil.extractUsername(webRequest), 
						Integer.parseInt(reservationId))));
	}
	
	@PutMapping("/describe")
	public ResponseEntity<ApiResponse<TaskDto>> updateDescription(final WebRequest webRequest, 
			@RequestBody @Valid final TaskUpdateDescriptionRequest taskUpdateDescriptionRequest) {
		log.info("** Update worker description on a reservation details.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerReservationTaskService.updateDescription(taskUpdateDescriptionRequest)));
	}
	
	@PutMapping("/begin")
	public ResponseEntity<ApiResponse<TaskDto>> beginTask(final WebRequest webRequest, 
			@RequestBody @Valid final TaskBeginEndRequest taskBeginRequest) {
		log.info("** Begin a worker task.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerReservationTaskService.beginTask(taskBeginRequest)));
	}
	
	@PutMapping("/end")
	public ResponseEntity<ApiResponse<TaskDto>> endTask(final WebRequest webRequest, 
			@RequestBody @Valid final TaskBeginEndRequest taskEndRequest) {
		log.info("** End a worker task.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerReservationTaskService.endTask(taskEndRequest)));
	}
	
	
	
}













