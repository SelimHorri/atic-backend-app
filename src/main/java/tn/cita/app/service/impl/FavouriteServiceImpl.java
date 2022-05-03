package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.FavouriteDto;
import tn.cita.app.mapper.FavouriteMapper;
import tn.cita.app.repository.FavouriteRepository;
import tn.cita.app.service.FavouriteService;

@Service
@Transactional
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {
	
	private final FavouriteRepository favouriteRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<FavouriteDto> findAllByCustomerId(final Integer customerId) {
		return this.favouriteRepository.findAllByCustomerId(customerId)
				.stream()
					.map(FavouriteMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}













