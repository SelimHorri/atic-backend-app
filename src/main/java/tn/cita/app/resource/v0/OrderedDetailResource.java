package tn.cita.app.resource.v0;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
import tn.cita.app.constant.AppConstant;
import tn.cita.app.domain.id.OrderedDetailId;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.request.OrderedDetailRequest;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.v0.OrderedDetailService;
import tn.cita.app.util.UserRequestExtractorUtil;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/ordered-details")
@RequiredArgsConstructor
public class OrderedDetailResource {
	
	@Qualifier("customerRequestExtractorUtil")
	private final UserRequestExtractorUtil extractorUtil;
	private final OrderedDetailService orderedDetailService;
	
	@GetMapping("/reservationId/{reservationId}")
	public ResponseEntity<ApiPayloadResponse<Page<OrderedDetailDto>>> findAllByReservationId(final WebRequest request, 
			@PathVariable final String reservationId) {
		this.extractorUtil.extractUsername(request);
		final var orderedDetailDtos = this.orderedDetailService.findAllByReservationId(Integer.parseInt(reservationId));
		return ResponseEntity.ok(new ApiPayloadResponse<>(orderedDetailDtos.toList().size(), HttpStatus.OK, true, orderedDetailDtos));
	}
	
	@DeleteMapping
	public ResponseEntity<ApiPayloadResponse<Boolean>> deleteById(final WebRequest request, 
			@RequestBody @Valid final OrderedDetailId orderedDetailId) {
		this.extractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.orderedDetailService.deleteById(orderedDetailId)));
	}
	
	@PostMapping
	public ResponseEntity<ApiPayloadResponse<OrderedDetailDto>> save(final WebRequest request, 
			@RequestBody @Valid final OrderedDetailRequest orderedDetailRequest) {
		this.extractorUtil.extractUsername(request);
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, this.orderedDetailService.save(orderedDetailRequest)));
	}
	
	
	
}













