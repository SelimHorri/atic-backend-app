package tn.cita.app.service.v0.business.employee.worker;

import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.request.WorkerProfileRequest;
import tn.cita.app.model.dto.response.WorkerProfileResponse;

public interface WorkerProfileService {
	
	WorkerProfileResponse fetchProfile(final String username);
	EmployeeDto updateProfileInfo(final WorkerProfileRequest workerProfileRequest);
	
}





