package tn.cita.app.resource.v0;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.ServiceDetailDto;
import tn.cita.app.dto.response.ServiceDetailsReservationContainerResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.ServiceDetailService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/service-details")
@RequiredArgsConstructor
public class ServiceDetailResource {
	
	private final ServiceDetailService serviceDetailService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiPayloadResponse<ServiceDetailDto>> findById(@PathVariable final String id) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.serviceDetailService.findById(Integer.parseInt(id))));
	}
	
	/**
	 * Secured Resource
	 * Added WHITE_BLACKLISTED_URLS_GET
	 * @param reservationId
	 * @return related services by a reservation
	 */
	@GetMapping("/reservationId/{reservationId}")
	public ResponseEntity<ApiPayloadResponse<ServiceDetailsReservationContainerResponse>> getOrderedServiceDetailsByReservationId(
			@PathVariable final String reservationId) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.serviceDetailService.getOrderedServiceDetailsByReservationId(Integer.parseInt(reservationId))));
	}
	
	@GetMapping("/saloonId/{saloonId}")
	public ResponseEntity<ApiPayloadResponse<List<ServiceDetailDto>>> findAllByCategorySaloonId(@PathVariable final String saloonId) {
		final var serviceDetails = this.serviceDetailService.findAllByCategorySaloonId(Integer.parseInt(saloonId));
		return ResponseEntity.ok(new ApiPayloadResponse<>(serviceDetails.size(), HttpStatus.OK, true, serviceDetails));
	}
	
	
	
}















