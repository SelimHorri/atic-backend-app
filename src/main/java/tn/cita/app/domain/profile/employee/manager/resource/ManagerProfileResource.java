package tn.cita.app.domain.profile.employee.manager.resource;

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
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.domain.profile.employee.manager.model.ManagerProfileRequest;
import tn.cita.app.domain.profile.employee.manager.model.ManagerProfileResponse;
import tn.cita.app.domain.profile.employee.manager.service.ManagerProfileService;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees/managers/profile")
@Slf4j
@RequiredArgsConstructor
public class ManagerProfileResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerProfileService managerProfileService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<ManagerProfileResponse>> fetchProfile(final WebRequest webRequest) {
		log.info("** Fetch manager profile info.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerProfileService.fetchProfile(this.userRequestExtractorUtil.extractUsername(webRequest))));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<EmployeeDto>> updateProfileInfo(final WebRequest webRequest, 
			@RequestBody @Valid final ManagerProfileRequest managerProfileRequest) {
		log.info("** Update manager profile info.. *\n");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerProfileService.updateProfileInfo(managerProfileRequest)));
	}
	
}






