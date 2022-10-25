package tn.cita.app.service.v0.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.domain.id.FavouriteId;
import tn.cita.app.dto.FavouriteDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.exception.wrapper.FavouriteNotFoundException;
import tn.cita.app.mapper.FavouriteMapper;
import tn.cita.app.repository.FavouriteRepository;
import tn.cita.app.service.v0.FavouriteService;
import tn.cita.app.util.ClientRequestUtils;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {
	
	private final FavouriteRepository favouriteRepository;
	
	@Override
	public FavouriteRepository getFavouriteRepository() {
		return this.favouriteRepository;
	}
	
	@Override
	public FavouriteDto findById(final FavouriteId favouriteId) {
		return this.favouriteRepository.findById(favouriteId)
				.map(FavouriteMapper::map)
				.orElseThrow(() -> new FavouriteNotFoundException("Favourite not found"));
	}
	
	@Override
	public Page<FavouriteDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest) {
		log.info("** Find all favourites by customerId.. *\n");
		return this.favouriteRepository.findAllByCustomerId(customerId, 
					ClientRequestUtils.from(clientPageRequest))
				.map(FavouriteMapper::map);
	}
	
	@Transactional
	@Override
	public Boolean deleteById(final FavouriteId favouriteId) {
		log.info("** Delete favourite by id.. *\n");
		this.favouriteRepository.deleteById(favouriteId);
		return !this.favouriteRepository.existsById(favouriteId);
	}
	
	
	
}













