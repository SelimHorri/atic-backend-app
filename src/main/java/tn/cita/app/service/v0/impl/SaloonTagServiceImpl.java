package tn.cita.app.service.v0.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.SaloonTagDto;
import tn.cita.app.mapper.SaloonTagMapper;
import tn.cita.app.repository.SaloonTagRepository;
import tn.cita.app.service.v0.SaloonTagService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class SaloonTagServiceImpl implements SaloonTagService {
	
	private final SaloonTagRepository saloonTagRepository;
	
	@Override
	public List<SaloonTagDto> findAllBySaloonId(final Integer saloonId) {
		log.info("** Find all saloonTags by saloonId.. *\n");
		return this.saloonTagRepository.findAllBySaloonId(saloonId).stream()
				.map(SaloonTagMapper::map)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}
















