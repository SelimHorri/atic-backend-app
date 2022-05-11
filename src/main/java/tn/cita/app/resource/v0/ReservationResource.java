package tn.cita.app.resource.v0;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.ReservationDto;
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
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> findById(@PathVariable("id") final String id, 
			final HttpServletRequest request) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> findByCode(@PathVariable("code") final String code, 
			final HttpServletRequest request) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findByCode(code)));
	}
	
	@GetMapping("/details/{reservationId}")
	public ResponseEntity<ApiPayloadResponse<ReservationContainerResponse>> getReservationDetails(
			@PathVariable("reservationId") final String reservationId,
			final HttpServletRequest request) {
		this.userRequestExtractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.getReservationDetails(Integer.parseInt(reservationId))));
	}
	
	
	
}













