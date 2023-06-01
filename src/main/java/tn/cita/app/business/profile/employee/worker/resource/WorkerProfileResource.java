package tn.cita.app.business.profile.employee.worker.resource;

import jakarta.validation.Valid;

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
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.business.profile.employee.worker.model.WorkerProfileRequest;
import tn.cita.app.business.profile.employee.worker.model.WorkerProfileResponse;
import tn.cita.app.business.profile.employee.worker.service.WorkerProfileService;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees/workers/profile")
@Slf4j
@RequiredArgsConstructor
public class WorkerProfileResource {
	
	@Qualifier("workerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final WorkerProfileService workerProfileService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<WorkerProfileResponse>> fetchProfile(final WebRequest webRequest) {
		log.info("** Fetch worker profile info.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerProfileService.fetchProfile(this.userRequestExtractorUtil.extractUsername(webRequest))));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<EmployeeDto>> updateProfileInfo(final WebRequest webRequest, 
			@RequestBody @Valid final WorkerProfileRequest workerProfileRequest) {
		log.info("** Update worker profile info.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.workerProfileService.updateProfileInfo(workerProfileRequest)));
	}
	
}




