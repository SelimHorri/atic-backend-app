package tn.cita.app.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
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
	public Page<RatingDto> findAllByCustomerId(final Integer customerId) {
		return this.ratingRepository.findAllByCustomerId(customerId, PageRequest.of(1 - 1, AppConstant.PAGE_SIZE))
				.map(RatingMapper::map);
	}
	
	
	
}














