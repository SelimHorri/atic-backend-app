package tn.cita.app.resource.v0;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.TaskService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/tasks")
@RequiredArgsConstructor
public class TaskResource {
	
	@Qualifier("customerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final TaskService taskService;
	
	@GetMapping("/reservationId/{reservationId}")
	public ResponseEntity<ApiPayloadResponse<List<TaskDto>>> findAllByReservationId(
			@PathVariable("reservationId") final String reservationId, 
			final HttpServletRequest request) {
		this.userRequestExtractorUtil.extractUsername(request);
		final var taskDtos = this.taskService.findAllByReservationId(Integer.parseInt(reservationId));
		return ResponseEntity.ok(new ApiPayloadResponse<>(taskDtos.size(), HttpStatus.OK, true, taskDtos));
	}
	
	
	
}















