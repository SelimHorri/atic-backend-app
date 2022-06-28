package tn.cita.app.resource.v0.business.employee.manager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.response.ManagerWorkerInfoResponse;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.employee.manager.ManagerWorkerInfoService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/employees/managers/workers")
@RequiredArgsConstructor
public class ManagerWorkerInfoResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerWorkerInfoService managerWorkerInfoService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<ManagerWorkerInfoResponse>> getAllSubWorkers(final WebRequest webRequest) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, this.managerWorkerInfoService
				.getAllSubWorkers(this.userRequestExtractorUtil.extractUsername(webRequest))));
	}
	
	
	
}














