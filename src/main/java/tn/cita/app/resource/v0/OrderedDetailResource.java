package tn.cita.app.resource.v0;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import tn.cita.app.model.domain.id.OrderedDetailId;
import tn.cita.app.model.dto.OrderedDetailDto;
import tn.cita.app.model.dto.request.OrderedDetailRequest;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.service.OrderedDetailService;

@RestController
@RequestMapping("${app.api-version}" + "/ordered-details")
@Slf4j
@RequiredArgsConstructor
public class OrderedDetailResource {
	
	private final OrderedDetailService orderedDetailService;
	
	@GetMapping("/identifier/{identifier}")
	public ResponseEntity<ApiResponse<OrderedDetailDto>> findByIdentifier(@PathVariable final String identifier) {
		log.info("** Find by identifier.. *");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.orderedDetailService.findByIdentifier(identifier.strip())));
	}
	
	@GetMapping("/reservationId/{reservationId}")
	public ResponseEntity<ApiResponse<Page<OrderedDetailDto>>> findAllByReservationId(
			final WebRequest request, @PathVariable final String reservationId) {
		log.info("** Find all ordered details by reservationId.. *");
		final var orderedDetailDtos = this.orderedDetailService.findAllByReservationId(Integer.parseInt(reservationId));
		return ResponseEntity.ok(new ApiResponse<>(orderedDetailDtos.size(), HttpStatus.OK, true, new PageImpl<>(orderedDetailDtos)));
	}
	
	@DeleteMapping
	public ResponseEntity<ApiResponse<Boolean>> deleteById(
			final WebRequest request, @RequestBody @Valid final OrderedDetailId orderedDetailId) {
		log.info("** Delete an ordered detail by id.. *");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.orderedDetailService.deleteById(orderedDetailId)));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<OrderedDetailDto>> save(
			final WebRequest request, @RequestBody @Valid final OrderedDetailRequest orderedDetailRequest) {
		log.info("** Save an ordered detail.. *");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true,
				this.orderedDetailService.save(orderedDetailRequest)));
	}
	
}




