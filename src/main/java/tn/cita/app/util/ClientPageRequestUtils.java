package tn.cita.app.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.NonNull;
import tn.cita.app.dto.request.ClientPageRequest;

public interface ClientPageRequestUtils {
	
	public static Pageable from(@NonNull final ClientPageRequest clientPageRequest) {
		return PageRequest.of(clientPageRequest.getOffset() - 1, 
				clientPageRequest.getSize(), 
				clientPageRequest.getSortDirection(), 
				clientPageRequest.getSortBy());
	}
	
	
	
}










