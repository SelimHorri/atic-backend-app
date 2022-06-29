package tn.cita.app.resource.v0.business.employee.manager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.ManagerWorkerAssignmentResponse;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.employee.manager.ManagerWorkerAssignmentService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/employees/managers/workers/assignments")
@RequiredArgsConstructor
public class ManagerWorkerAssignmentResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerWorkerAssignmentService managerWorkerAssignmentService;
	
	@GetMapping("/{workerId}")
	public ResponseEntity<ApiResponse<ManagerWorkerAssignmentResponse>> getAllWorkerTasks(final WebRequest webRequest, 
			@PathVariable final String workerId, 
			@RequestParam final Map<String, String> params) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true,this.managerWorkerAssignmentService
				.getAllWorkerTasks(this.userRequestExtractorUtil.extractUsername(webRequest), 
						Integer.parseInt(workerId), new ClientPageRequest(params))));
	}
	
	
	
}













