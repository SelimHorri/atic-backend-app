package tn.cita.app.util;

import org.springframework.web.context.request.WebRequest;

public interface UserRequestExtractorUtil {
	
	String extractUsername(final WebRequest request);
	
}


