package tn.cita.app.service.v0.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.id.FavouriteId;
import tn.cita.app.dto.FavouriteDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.mapper.FavouriteMapper;
import tn.cita.app.repository.FavouriteRepository;
import tn.cita.app.service.v0.FavouriteService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {
	
	private final FavouriteRepository favouriteRepository;
	
	@Override
	public Page<FavouriteDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest) {
		return this.favouriteRepository.findAllByCustomerId(customerId, 
					PageRequest.of(clientPageRequest.getOffset() - 1, 
							clientPageRequest.getSize(), 
							clientPageRequest.getSortDirection(), 
							clientPageRequest.getSortBy()))
				.map(FavouriteMapper::map);
	}
	
	@Transactional
	@Override
	public Boolean deleteById(final FavouriteId favouriteId) {
		this.favouriteRepository.deleteById(favouriteId);
		return !this.favouriteRepository.existsById(favouriteId);
	}
	
	
	
}













