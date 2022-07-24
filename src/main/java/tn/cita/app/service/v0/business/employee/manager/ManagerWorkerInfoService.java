package tn.cita.app.service.v0.business.employee.manager;

import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.response.ManagerWorkerInfoResponse;

public interface ManagerWorkerInfoService {
	
	ManagerWorkerInfoResponse fetchAllSubWorkers(final String username);
	EmployeeDto fetchWorkerInfo(final Integer workerId);
	
}












