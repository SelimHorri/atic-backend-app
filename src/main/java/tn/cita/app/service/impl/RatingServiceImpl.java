package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.RatingDto;
import tn.cita.app.mapper.RatingMapper;
import tn.cita.app.repository.RatingRepository;
import tn.cita.app.service.RatingService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
	
	private final RatingRepository ratingRepository;
	
	@Override
	public List<RatingDto> findAllByCustomerId(final Integer customerId) {
		return this.ratingRepository.findAllByCustomerId(customerId)
				.stream()
					.map(RatingMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}














