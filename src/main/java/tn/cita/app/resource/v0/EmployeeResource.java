package tn.cita.app.resource.v0;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.EmployeeService;

@RestController
@RequestMapping(AppConstants.API_CONTEXT_V0 + "/employees")
@Slf4j
@RequiredArgsConstructor
public class EmployeeResource {
	
	private final EmployeeService employeeService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeDto>> findById(@PathVariable final String id) {
		log.info("** Find employee by id.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.employeeService.findById(Integer.parseInt(id))));
	}
	
	@GetMapping("/identifier/{identifier}")
	public ResponseEntity<ApiResponse<EmployeeDto>> findByIdentifier(@PathVariable final String identifier) {
		log.info("** Find employee by identifier.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.employeeService.findByIdentifier(identifier)));
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<ApiResponse<EmployeeDto>> findByCredentialUsername(@PathVariable final String username) {
		log.info("** Find employee by credential username.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				this.employeeService.findByCredentialUsername(username)));
	}
	
	@GetMapping("/ssn/{ssn}")
	public ResponseEntity<ApiResponse<Page<EmployeeDto>>> findAllBySsn(@PathVariable final String ssn) {
		log.info("** Find employee(s) by ssn.. *\n");
		return ResponseEntity.ok(new ApiResponse<>(1, HttpStatus.OK, true, 
				new PageImpl<>(this.employeeService.findAllBySsn(ssn))));
	}
	
	
	
}
















