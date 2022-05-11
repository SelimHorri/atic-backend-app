package tn.cita.app.util.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tn.cita.app.util.UserRequestExtractorUtil;

@Component
@RequiredArgsConstructor
public class ManagerRequestExtractorUtil implements UserRequestExtractorUtil {
	
	@Override
	public String extractUsername(final HttpServletRequest request) {
		return "Hello Manager";
	}
	
	
	
}











