package tn.cita.app.service.v0.business.employee.manager;

import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.request.ManagerProfileRequest;
import tn.cita.app.model.dto.response.ManagerProfileResponse;

public interface ManagerProfileService {
	
	ManagerProfileResponse fetchProfile(final String username);
	EmployeeDto updateProfileInfo(final ManagerProfileRequest managerProfileRequest);
	
}






