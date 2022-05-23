package tn.cita.app.service;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.TagDto;

public interface TagService {
	
	Page<TagDto> findAll(final int pageOffset);
	TagDto findById(final Integer id);
	
}











