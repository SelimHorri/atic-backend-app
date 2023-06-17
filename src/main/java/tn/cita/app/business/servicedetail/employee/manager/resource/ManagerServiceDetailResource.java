package tn.cita.app.business.servicedetail.employee.manager.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import tn.cita.app.business.servicedetail.employee.manager.model.ServiceDetailRequest;
import tn.cita.app.business.servicedetail.employee.manager.service.ManagerServiceDetailService;
import tn.cita.app.model.dto.ServiceDetailDto;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping("${app.api-version}" + "/employees/managers/service-details")
@Slf4j
@RequiredArgsConstructor
public class ManagerServiceDetailResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerServiceDetailService managerServiceDetailService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<ServiceDetailDto>>> fetchAll(final WebRequest webRequest) {
		log.info("** Fetch all service details by manager.. *");
		return ResponseEntity.ok(new ApiResponse<>(0, HttpStatus.OK, true, 
				this.managerServiceDetailService.fetchAll(this.userRequestExtractorUtil.extractUsername(webRequest))));
	}
	
	@GetMapping("/{serviceDetailId}")
	public ResponseEntity<ApiResponse<ServiceDetailDto>> fetchById(
			final WebRequest webRequest, @PathVariable final String serviceDetailId) {
		log.info("** Fetch service detail by id by manager.. *");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerServiceDetailService.fetchById(Integer.parseInt(serviceDetailId))));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<ServiceDetailDto>> saveServiceDetail(
			final WebRequest webRequest, @RequestBody @Valid final ServiceDetailRequest serviceDetailRequest) {
		log.info("** Save service detail by manager.. *");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerServiceDetailService.saveServiceDetail(serviceDetailRequest)));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<ServiceDetailDto>> updateServiceDetail(
			final WebRequest webRequest, @RequestBody @Valid final ServiceDetailRequest serviceDetailRequest) {
		log.info("** Update service detail by manager.. *");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerServiceDetailService.updateServiceDetail(serviceDetailRequest)));
	}
	
	@DeleteMapping("/{serviceDetailId}")
	public ResponseEntity<ApiResponse<Boolean>> deleteServiceDetail(final WebRequest webRequest, @PathVariable final String serviceDetailId) {
		log.info("** Delete service detail by manager.. *");
		this.userRequestExtractorUtil.extractUsername(webRequest);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerServiceDetailService.deleteServiceDetail(Integer.parseInt(serviceDetailId))));
	}
	
}




