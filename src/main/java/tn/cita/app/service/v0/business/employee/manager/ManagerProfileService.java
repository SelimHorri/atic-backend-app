package tn.cita.app.service.v0.business.employee.manager;

import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.request.ManagerProfileRequest;
import tn.cita.app.dto.response.ManagerProfileResponse;

public interface ManagerProfileService {
	
	ManagerProfileResponse getProfile(final String username);
	EmployeeDto updateProfileInfo(final ManagerProfileRequest managerProfileRequest);
	
}











