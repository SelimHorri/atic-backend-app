package tn.cita.app.util.impl;

import javax.servlet.http.HttpServletRequest;

import tn.cita.app.util.UserRequestExtractorUtil;

public class WorkerRequestExtractorUtil implements UserRequestExtractorUtil {
	
	@Override
	public String extractUsername(final HttpServletRequest request) {
		return "Hello Worker";
	}
	
}











