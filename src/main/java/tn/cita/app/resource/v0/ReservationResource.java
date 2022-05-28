package tn.cita.app.resource.v0;

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
import tn.cita.app.dto.response.ReservationContainerResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.ReservationService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/reservations")
@RequiredArgsConstructor
public class ReservationResource {
	
	@Qualifier("customerRequestExtractorUtil")
	private final UserRequestExtractorUtil userRequestExtractorUtil;
	private final ReservationService reservationService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> findById(final WebRequest request, 
			@PathVariable final String id) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> findByCode(final WebRequest request,
			@PathVariable final String code) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findByCode(code)));
	}
	
	@GetMapping("/details/{reservationId}")
	public ResponseEntity<ApiPayloadResponse<ReservationContainerResponse>> getReservationDetails(final WebRequest request,
			@PathVariable final String reservationId) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.getReservationDetails(Integer.parseInt(reservationId))));
	}
	
	@PutMapping("/details")
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> updateReservationDetails(final WebRequest request, 
			@RequestBody @Valid final ReservationDetailRequest reservationDetailRequest) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.updateReservationDetails(reservationDetailRequest)));
	}
	
	@PutMapping("/cancel")
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> cancelReservation(final WebRequest request, 
			@RequestBody @Valid final ReservationDto reservationDtoRequest) {
		this.userRequestExtractorUtil.extractUsername(request);
		final var reservationDto = this.reservationService.cancelReservation(reservationDtoRequest);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, reservationDto));
	}
	
	
	
}













