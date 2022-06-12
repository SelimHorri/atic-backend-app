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
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ReservationDetailRequest;
import tn.cita.app.dto.response.ReservationDetailResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.v0.business.customer.CustomerReservationDetailService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/customers/reservations/details")
@RequiredArgsConstructor
public class CustomerReservationDetailResource {
	
	@Qualifier("customerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final CustomerReservationDetailService customerReservationDetailService;
	
	@GetMapping("/{reservationId}")
	public ResponseEntity<ApiPayloadResponse<ReservationDetailResponse>> getReservationDetails(final WebRequest request,
			@PathVariable final String reservationId) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerReservationDetailService.getReservationDetails(Integer.parseInt(reservationId))));
	}
	
	@PutMapping
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> updateReservationDetails(final WebRequest request, 
			@RequestBody @Valid final ReservationDetailRequest reservationDetailRequest) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerReservationDetailService.updateReservationDetails(reservationDetailRequest)));
	}
	
	
	
}
















