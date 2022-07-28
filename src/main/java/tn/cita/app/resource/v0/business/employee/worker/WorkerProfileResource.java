package tn.cita.app.resource.v0.business.employee.worker;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.request.WorkerProfileRequest;
import tn.cita.app.dto.response.WorkerProfileResponse;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.employee.worker.WorkerProfileService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees/workers/profile")
@RequiredArgsConstructor
public class WorkerProfileResource {
	
	@Qualifier("workerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final WorkerProfileService workerProfileService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<WorkerProfileResponse>> fetchProfile(final WebRequest webRequest) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerProfileService.fetchProfile(this.userRequestExtractorUtil.extractUsername(webRequest))));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<EmployeeDto>> updateProfileInfo(final WebRequest webRequest, 
			@RequestBody @Valid final WorkerProfileRequest workerProfileRequest) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerProfileService.updateProfileInfo(workerProfileRequest)));
	}
	
	
	
}














