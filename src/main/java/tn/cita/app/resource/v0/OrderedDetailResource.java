package tn.cita.app.resource.v0;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.OrderedDetailService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/ordered-details")
@RequiredArgsConstructor
public class OrderedDetailResource {
	
	private final OrderedDetailService orderedDetailService;
	
	@GetMapping("/reservationId/{reservationId}")
	public ResponseEntity<ApiPayloadResponse<List<OrderedDetailDto>>> findAllByReservationId(@PathVariable final String reservationId) {
		final var orderedDetailDtos = this.orderedDetailService.findAllByReservationId(Integer.parseInt(reservationId));
		return ResponseEntity.ok(new ApiPayloadResponse<>(orderedDetailDtos.size(), HttpStatus.OK, true, orderedDetailDtos));
	}
	
	
	
}













