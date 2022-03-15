package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.TagDto;
import tn.cita.app.exception.wrapper.TagNotFoundException;
import tn.cita.app.mapper.TagMapper;
import tn.cita.app.repository.TagRepository;
import tn.cita.app.service.TagService;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
	
	private final TagRepository tagRepository;
	
	@Override
	public List<TagDto> findAll(final int pageOffset) {
		log.info("** TagServiceImpl; List TagDto; find all based on pageOffset.. *\n");
		return this.tagRepository.findAll(PageRequest.of(pageOffset - 1, AppConstant.PAGE_SIZE))
				.stream()
					.map(TagMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
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











