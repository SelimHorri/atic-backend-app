package tn.cita.app.resource.v0.business.employee.manager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.ManagerReservationResponse;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.employee.manager.ManagerReservationService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/employees/managers/reservations")
@RequiredArgsConstructor
public class ManagerReservationResource {
	
	@Qualifier("managerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ManagerReservationService managerReservationService;
	
	@GetMapping("/paged")
	public ResponseEntity<ApiResponse<ManagerReservationResponse>> getAllReservations(final WebRequest webRequest, 
			@RequestParam final Map<String, String> params) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationService.getAllReservations(this.userRequestExtractorUtil.extractUsername(webRequest), 
						new ClientPageRequest(params))));
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse<ManagerReservationResponse>> getAllReservations(final WebRequest webRequest) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.managerReservationService.getAllReservations(this.userRequestExtractorUtil.extractUsername(webRequest), null)));
	}
	
	
	
}


















