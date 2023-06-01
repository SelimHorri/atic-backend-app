package tn.cita.app.resource.v0;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.domain.id.OrderedDetailId;
import tn.cita.app.model.dto.OrderedDetailDto;
import tn.cita.app.model.dto.request.OrderedDetailRequest;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.service.OrderedDetailService;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/ordered-details")
@Slf4j
@RequiredArgsConstructor
public class OrderedDetailResource {
	
	private final OrderedDetailService orderedDetailService;
	
	@GetMapping("/identifier/{identifier}")
	public ResponseEntity<ApiResponse<OrderedDetailDto>> findByIdentifier(@PathVariable final String identifier) {
		log.info("** Find by identifier.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.orderedDetailService.findByIdentifier(identifier.strip())));
	}
	
	@GetMapping("/reservationId/{reservationId}")
	public ResponseEntity<ApiResponse<Page<OrderedDetailDto>>> findAllByReservationId(
			final WebRequest request, @PathVariable final String reservationId) {
		log.info("** Find all ordered details by reservationId.. *\n");
		final var orderedDetailDtos = this.orderedDetailService.findAllByReservationId(Integer.parseInt(reservationId));
		return ResponseEntity.ok(new ApiResponse<>(orderedDetailDtos.size(), HttpStatus.OK, true, new PageImpl<>(orderedDetailDtos)));
	}
	
	@DeleteMapping
	public ResponseEntity<ApiResponse<Boolean>> deleteById(
			final WebRequest request, @RequestBody @Valid final OrderedDetailId orderedDetailId) {
		log.info("** Delete an ordered detail by id.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.orderedDetailService.deleteById(orderedDetailId)));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<OrderedDetailDto>> save(
			final WebRequest request, @RequestBody @Valid final OrderedDetailRequest orderedDetailRequest) {
		log.info("** Save an ordered detail.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true,
				this.orderedDetailService.save(orderedDetailRequest)));
	}
	
}




