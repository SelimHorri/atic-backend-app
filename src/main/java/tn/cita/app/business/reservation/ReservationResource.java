package tn.cita.app.business.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.response.api.ApiResponse;

import java.util.Map;

@RestController
@RequestMapping("${app.api-version}" + "/reservations")
@Slf4j
@RequiredArgsConstructor
public class ReservationResource {
	
	private final ReservationService reservationService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ReservationDto>> findById(final WebRequest request, @PathVariable final String id) {
		log.info("** Find a reservation by id.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/identifier/{identifier}")
	public ResponseEntity<ApiResponse<ReservationDto>> findByIdentifier(@PathVariable final String identifier) {
		log.info("** Find by identifier.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findByIdentifier(identifier.strip())));
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<ApiResponse<ReservationDto>> findByCode(final WebRequest request, @PathVariable final String code) {
		log.info("** Find a reservation by code.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.reservationService.findByCode(code)));
	}
	
	@GetMapping("/saloonId/{saloonId}")
	public ResponseEntity<ApiResponse<Page<ReservationDto>>> findAllBySaloonId(
			final WebRequest request,
			@PathVariable final String saloonId, 
			@RequestParam(required = false) final Map<String, String> params) {
		log.info("** Find all reservations by saloonId.. *\n");
		final var reservations = this.reservationService.findAllBySaloonId(Integer.parseInt(saloonId));
		return ResponseEntity.ok(new ApiResponse<>((int)reservations.size(), HttpStatus.OK, true, new PageImpl<>(reservations)));
	}
	
}




