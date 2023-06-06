package tn.cita.app.service;

import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.response.ManagerWorkerInfoResponse;

public interface ManagerWorkerInfoService {
	
	ManagerWorkerInfoResponse fetchAllSubWorkers(final String username);
	EmployeeDto fetchWorkerInfo(final Integer workerId);
	
}


