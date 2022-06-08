package tn.cita.app.resource.v0.business.customer;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.request.ReservationRequest;
import tn.cita.app.dto.response.CustomerReservationResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.v0.business.customer.CustomerReservationService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/customers/reservations")
@RequiredArgsConstructor
public class CustomerReservationResource {
	
	@Qualifier("customerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final CustomerReservationService customerReservationService;
	
	@GetMapping
	public ResponseEntity<ApiPayloadResponse<CustomerReservationResponse>> getReservations(final WebRequest request,
			@RequestParam final Map<String, String> params) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.customerReservationService.getReservationsByUsername(this.userRequestExtractorUtil.extractUsername(request), 
						new ClientPageRequest(params))));
	}
	
	@PutMapping("/cancel")
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> cancelReservation(final WebRequest request, 
			@RequestBody @Valid final ReservationDto reservationDtoRequest) {
		this.userRequestExtractorUtil.extractUsername(request);
		final var reservationDto = this.customerReservationService.cancelReservation(reservationDtoRequest);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, reservationDto));
	}
	
	@PostMapping
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> createReservation(final WebRequest request,
			@RequestBody @NotNull @Valid final ReservationRequest reservationRequest) {
		this.userRequestExtractorUtil.extractUsername(request);
		final var reservationDto = this.customerReservationService.createReservation(reservationRequest);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, reservationDto));
	}
	
	
	
}















