package tn.cita.app.service.v0.business.employee.worker;

import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.request.WorkerProfileRequest;
import tn.cita.app.dto.response.WorkerProfileResponse;

public interface WorkerProfileService {
	
	WorkerProfileResponse fetchProfile(final String username);
	EmployeeDto updateProfileInfo(final WorkerProfileRequest workerProfileRequest);
	
}










