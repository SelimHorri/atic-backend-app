package tn.cita.app.domain.profile.employee.worker.service;

import tn.cita.app.domain.profile.employee.worker.model.WorkerProfileRequest;
import tn.cita.app.domain.profile.employee.worker.model.WorkerProfileResponse;
import tn.cita.app.model.dto.EmployeeDto;

public interface WorkerProfileService {
	
	WorkerProfileResponse fetchProfile(final String username);
	EmployeeDto updateProfileInfo(final WorkerProfileRequest workerProfileRequest);
	
}





