package tn.cita.app.resource.v0;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.dto.ServiceDetailDto;
import tn.cita.app.model.dto.response.ServiceDetailsReservationContainerResponse;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.ServiceDetailService;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/service-details")
@Slf4j
@RequiredArgsConstructor
public class ServiceDetailResource {
	
	private final ServiceDetailService serviceDetailService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ServiceDetailDto>> findById(@PathVariable final String id) {
		log.info("** Find by id.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.serviceDetailService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/identifier/{identifier}")
	public ResponseEntity<ApiResponse<ServiceDetailDto>> findByIdentifier(@PathVariable final String identifier) {
		log.info("** Find by identifier.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.serviceDetailService.findByIdentifier(identifier.strip())));
	}
	
	/**
	 * Secured Resource
	 * Added WHITE_BLACKLISTED_URLS_GET
	 * @param reservationId
	 * @return related services by a reservation
	 */
	@GetMapping("/reservationId/{reservationId}")
	public ResponseEntity<ApiResponse<ServiceDetailsReservationContainerResponse>> fetchOrderedServiceDetails(
			@PathVariable final String reservationId) {
		log.info("** Fetch ordered service details by reservationId (secured api).. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.serviceDetailService.fetchOrderedServiceDetails(Integer.parseInt(reservationId))));
	}
	
	/**
	 * Secured Resource
	 * Added WHITE_BLACKLISTED_URLS_GET
	 * @param reservationIdentifier
	 * @return related services by a reservation
	 */
	@GetMapping("/reservationIdentifier/{reservationIdentifier}")
	public ResponseEntity<ApiResponse<ServiceDetailsReservationContainerResponse>> fetchOrderedServiceDetailsWithIdentifier(
			@PathVariable final String reservationIdentifier) {
		log.info("** Fetch ordered service details by reservationIdentifier (secured api).. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.serviceDetailService.fetchOrderedServiceDetails(reservationIdentifier.strip())));
	}
	
	@GetMapping("/saloonId/{saloonId}")
	public ResponseEntity<ApiResponse<Page<ServiceDetailDto>>> findAllByCategorySaloonId(@PathVariable final String saloonId) {
		log.info("** Find All service details by category saloonId.. *\n");
		final var serviceDetails = this.serviceDetailService.findAllByCategorySaloonId(Integer.parseInt(saloonId));
		return ResponseEntity.ok(new ApiResponse<>(serviceDetails.size(), HttpStatus.OK, true, new PageImpl<>(serviceDetails)));
	}
	
}







