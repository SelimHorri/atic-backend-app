package tn.cita.app.resource.v0.business.employee.worker;

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
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.TaskBeginRequest;
import tn.cita.app.dto.response.ReservationDetailResponse;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.employee.worker.WorkerReservationDetailService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/employees/workers/reservations/details")
@RequiredArgsConstructor
public class WorkerReservationDetailResource {
	
	@Qualifier("workerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final WorkerReservationDetailService workerReservationDetailService;
	
	@GetMapping("/{reservationId}")
	public ResponseEntity<ApiResponse<ReservationDetailResponse>> getReservationDetails(final WebRequest request,
			@PathVariable final String reservationId) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerReservationDetailService.getReservationDetails(Integer.parseInt(reservationId))));
	}
	
	@GetMapping("/tasks/{reservationId}")
	public ResponseEntity<ApiResponse<TaskDto>> getAssignedTask(final WebRequest webRequest, @PathVariable final String reservationId) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerReservationDetailService.getAssignedTask(this.userRequestExtractorUtil.extractUsername(webRequest), 
						Integer.parseInt(reservationId))));
	}
	
	@PutMapping("/tasks/begin")
	public ResponseEntity<ApiResponse<TaskDto>> beginTask(final WebRequest webRequest, 
			@RequestBody final TaskBeginRequest taskBeginRequest) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerReservationDetailService.beginTask(taskBeginRequest)));
	}
	
	
	
}














