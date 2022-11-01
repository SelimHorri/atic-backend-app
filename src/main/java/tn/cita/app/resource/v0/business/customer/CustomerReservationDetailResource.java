package tn.cita.app.resource.v0.business.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ReservationDetailRequest;
import tn.cita.app.model.dto.response.ReservationDetailResponse;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.business.customer.CustomerReservationDetailService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/customers/reservations/details")
@Slf4j
@RequiredArgsConstructor
public class CustomerReservationDetailResource {
	
	@Qualifier("customerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final CustomerReservationDetailService customerReservationDetailService;
	
	@GetMapping("/{reservationId}")
	public ResponseEntity<ApiResponse<ReservationDetailResponse>> fetchReservationDetails(final WebRequest request,
			@PathVariable final String reservationId) {
		log.info("** Fetch customer reservation details.. *\n");
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.customerReservationDetailService.fetchReservationDetails(Integer.parseInt(reservationId))));
	}
	
	@GetMapping("/identifier/{reservationIdentifier}")
	public ResponseEntity<ApiResponse<ReservationDetailResponse>> fetchReservationDetailsWithIdentifier(final WebRequest request,
			@PathVariable final String reservationIdentifier) {
		log.info("** Fetch customer reservation details.. *\n");
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.customerReservationDetailService.fetchReservationDetails(reservationIdentifier.strip())));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<ReservationDto>> updateReservationDetails(final WebRequest request, 
			@RequestBody @Valid final ReservationDetailRequest reservationDetailRequest) {
		log.info("** Update customer reservation details.. *\n");
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.customerReservationDetailService.updateReservationDetails(reservationDetailRequest)));
	}
	
	
	
}
















