package tn.cita.app.service;

import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.TagDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface TagService {
	
	Page<TagDto> findAll(final ClientPageRequest clientPageRequest);
	TagDto findById(final Integer id);
	TagDto findByIdentifier(final String identifier);
	
}



