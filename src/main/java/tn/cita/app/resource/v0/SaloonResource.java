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
import tn.cita.app.dto.SaloonDto;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.SaloonService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/saloons")
@RequiredArgsConstructor
public class SaloonResource {
	
	private final SaloonService saloonService;
	
	@GetMapping("/offset/{offset}")
	public ResponseEntity<ApiPayloadResponse<List<SaloonDto>>> findAll(@PathVariable("offset") final String offset) {
		final var saloons = this.saloonService.findAll(Integer.parseInt(offset));
		return ResponseEntity.ok(new ApiPayloadResponse<>(saloons.size(), 
				HttpStatus.OK, true, saloons));
	}
	
	
	
}













