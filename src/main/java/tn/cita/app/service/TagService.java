package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.TagDto;

public interface TagService {
	
	List<TagDto> findAll(final int pageOffset);
	TagDto findById(final Integer id);
	
}











