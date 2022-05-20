package tn.cita.app.resource.v0;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.service.EmployeeService;

@RestController
@RequestMapping(AppConstant.API_CONTEXT_V0 + "/employees")
@RequiredArgsConstructor
public class EmployeeResource {
	
	private final EmployeeService employeeService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiPayloadResponse<EmployeeDto>> findById(@PathVariable final String id) {
		return ResponseEntity.ok(new ApiPayloadResponse<>(1, HttpStatus.OK, true, 
				this.employeeService.findById(Integer.parseInt(id))));
	}
	
	
	
}















