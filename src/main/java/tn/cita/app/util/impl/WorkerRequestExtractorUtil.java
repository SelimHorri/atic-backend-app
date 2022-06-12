package tn.cita.app.util.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import lombok.RequiredArgsConstructor;
import tn.cita.app.util.UserRequestExtractorUtil;

@Component
@RequiredArgsConstructor
public class WorkerRequestExtractorUtil implements UserRequestExtractorUtil {
	
	@Override
	public String extractUsername(final WebRequest request) {
		return "Hello Worker";
	}
	
	
	
}











