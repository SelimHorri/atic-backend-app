package tn.cita.app.resource.v0;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.TaskService;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/tasks")
@Slf4j
@RequiredArgsConstructor
public class TaskResource {
	
	private final TaskService taskService;
	
	@GetMapping("/reservationId/{reservationId}")
	public ResponseEntity<ApiResponse<Page<TaskDto>>> findAllByReservationId(final WebRequest webRequest, 
			@PathVariable final String reservationId) {
		log.info("** Find all tasks by reservationId.. *\n");
		final var taskDtos = this.taskService.findAllByReservationId(Integer.parseInt(reservationId));
		return ResponseEntity.ok(new ApiResponse<>(taskDtos.size(), HttpStatus.OK, true, new PageImpl<>(taskDtos)));
	}
	
	
	
}















