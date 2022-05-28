package tn.cita.app.service;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.TagDto;
import tn.cita.app.dto.request.ClientPageRequest;

public interface TagService {
	
	Page<TagDto> findAll(final ClientPageRequest clientPageRequest);
	TagDto findById(final Integer id);
	
}











