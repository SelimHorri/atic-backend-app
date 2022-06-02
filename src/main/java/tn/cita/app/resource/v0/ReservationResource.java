package tn.cita.app.resource.v0;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.v0.ReservationService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/reservations")
@RequiredArgsConstructor
public class ReservationResource {
	
	private final ReservationService reservationService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> findById(final WebRequest request, 
			@PathVariable final String id) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<ApiPayloadResponse<ReservationDto>> findByCode(final WebRequest request,
			@PathVariable final String code) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findByCode(code)));
	}
	
	
	
}













