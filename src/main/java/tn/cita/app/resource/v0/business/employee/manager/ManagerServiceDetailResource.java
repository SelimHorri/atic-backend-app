package tn.cita.app.resource.v0.business.employee.manager;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.dto.request.ServiceDetailRequest;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.employee.manager.ManagerServiceDetailService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees/managers/service-details")
@RequiredArgsConstructor
public class ManagerServiceDetailResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerServiceDetailService managerServiceDetailService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<ServiceDetailDto>>> getAll(final WebRequest webRequest) {
		return ResponseEntity.ok(new ApiResponse<>(0, HttpStatus.OK, true, 
				this.managerServiceDetailService.getAll(this.userRequestExtractorUtil.extractUsername(webRequest))));
	}
	
	@GetMapping("/{serviceDetailId}")
	public ResponseEntity<ApiResponse<ServiceDetailDto>> getById(final WebRequest webRequest, 
			@PathVariable final String serviceDetailId) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerServiceDetailService.getById(Integer.parseInt(serviceDetailId))));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<ServiceDetailDto>> saveServiceDetail(final WebRequest webRequest, 
			@RequestBody @Valid final ServiceDetailRequest serviceDetailRequest) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerServiceDetailService.saveServiceDetail(serviceDetailRequest)));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<ServiceDetailDto>> updateServiceDetail(final WebRequest webRequest, 
			@RequestBody @Valid final ServiceDetailRequest serviceDetailRequest) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerServiceDetailService.updateServiceDetail(serviceDetailRequest)));
	}
	
	@DeleteMapping("/{serviceDetailId}")
	public ResponseEntity<ApiResponse<Boolean>> deleteServiceDetail(final WebRequest webRequest, 
			@PathVariable final String serviceDetailId) {
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerServiceDetailService.deleteServiceDetail(Integer.parseInt(serviceDetailId))));
	}
	
	
	
}













