package tn.cita.app.resource.v0;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import tn.cita.app.model.dto.TaskDto;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.service.TaskService;

@RestController
@RequestMapping("${app.api-version}" + "/tasks")
@Slf4j
@RequiredArgsConstructor
public class TaskResource {
	
	private final TaskService taskService;
	
	@GetMapping("/identifier/{identifier}")
	public ResponseEntity<ApiResponse<TaskDto>> findByIdentifier(@PathVariable final String identifier) {
		log.info("** Find by identifier.. *");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.taskService.findByIdentifier(identifier.strip())));
	}
	
	@GetMapping("/reservationId/{reservationId}")
	public ResponseEntity<ApiResponse<Page<TaskDto>>> findAllByReservationId(
			final WebRequest webRequest, @PathVariable final String reservationId) {
		log.info("** Find all tasks by reservationId.. *");
		final var taskDtos = this.taskService.findAllByReservationId(Integer.parseInt(reservationId));
		return ResponseEntity.ok(new ApiResponse<>(taskDtos.size(), HttpStatus.OK, true, new PageImpl<>(taskDtos)));
	}
	
}




