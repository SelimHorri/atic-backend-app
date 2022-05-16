package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.id.FavouriteId;
import tn.cita.app.dto.FavouriteDto;
import tn.cita.app.mapper.FavouriteMapper;
import tn.cita.app.repository.FavouriteRepository;
import tn.cita.app.service.FavouriteService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {
	
	private final FavouriteRepository favouriteRepository;
	
	@Override
	public List<FavouriteDto> findAllByCustomerId(final Integer customerId) {
		return this.favouriteRepository.findAllByCustomerId(customerId)
				.stream()
					.map(FavouriteMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	@Transactional
	@Override
	public Boolean deleteById(final FavouriteId favouriteId) {
		this.favouriteRepository.deleteById(favouriteId);
		return !this.favouriteRepository.existsById(favouriteId);
	}
	
	
	
}













