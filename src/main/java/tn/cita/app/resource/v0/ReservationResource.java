package tn.cita.app.resource.v0;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.ReservationService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/reservations")
@RequiredArgsConstructor
public class ReservationResource {
	
	private final ReservationService reservationService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ReservationDto>> findById(final WebRequest request, 
			@PathVariable final String id) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<ApiResponse<ReservationDto>> findByCode(final WebRequest request,
			@PathVariable final String code) {
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findByCode(code)));
	}
	
	@GetMapping("/saloonId/{saloonId}")
	public ResponseEntity<ApiResponse<Page<ReservationDto>>> findAllBySaloonId(final WebRequest request,
			@PathVariable final String saloonId, 
			@RequestParam(required = false) final Map<String, String> params) throws JsonProcessingException {
		// final var reservations = this.reservationService.findAllBySaloonId(Integer.parseInt(saloonId), new ClientPageRequest());
		final var reservations = this.reservationService.findAllBySaloonId(Integer.parseInt(saloonId));
		return ResponseEntity.ok(new ApiResponse<>((int)reservations.size(), 
				HttpStatus.OK, true, new PageImpl<>(reservations)));
	}
	
	
	
}













