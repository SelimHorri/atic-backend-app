package tn.cita.app.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.TagDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.exception.wrapper.TagNotFoundException;
import tn.cita.app.mapper.TagMapper;
import tn.cita.app.repository.TagRepository;
import tn.cita.app.service.TagService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
	
	private final TagRepository tagRepository;
	
	@Override
	public Page<TagDto> findAll(final ClientPageRequest clientPageRequest) {
		log.info("** TagServiceImpl; List TagDto; find all based on pageOffset.. *\n");
		return this.tagRepository.findAll(PageRequest
					.of(clientPageRequest.getOffset() - 1, clientPageRequest.getSize()))
				.map(TagMapper::map);
	}
	
	@Override
	public TagDto findById(final Integer id) {
		log.info("** TagServiceImpl; TagDto; find by id.. *\n");
		return this.tagRepository.findById(id)
				.map(TagMapper::map)
				.orElseThrow(() -> new TagNotFoundException(String
						.format("Tag with id: %d not found", id)));
	}
	
	
	
}











