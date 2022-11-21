package tn.cita.app.service.v0.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.mapper.RatingMapper;
import tn.cita.app.model.dto.RatingDto;
import tn.cita.app.repository.RatingRepository;
import tn.cita.app.service.v0.RatingService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
	
	private final RatingRepository ratingRepository;
	
	@Override
	public List<RatingDto> findAllByCustomerId(final Integer customerId) {
		log.info("** Find all ratings by customerId.. *\n");
		return this.ratingRepository.findAllByCustomerId(customerId).stream()
				.map(RatingMapper::map)
				.distinct()
				.toList();
	}
	
	
	
}














