package tn.cita.app.domain.profile.employee.manager.service;

import tn.cita.app.domain.profile.employee.manager.model.ManagerProfileRequest;
import tn.cita.app.domain.profile.employee.manager.model.ManagerProfileResponse;
import tn.cita.app.model.dto.EmployeeDto;

public interface ManagerProfileService {
	
	ManagerProfileResponse fetchProfile(final String username);
	EmployeeDto updateProfileInfo(final ManagerProfileRequest managerProfileRequest);
	
}






