package tn.cita.app.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
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
	public Page<FavouriteDto> findAllByCustomerId(final Integer customerId) {
		return this.favouriteRepository.findAllByCustomerId(customerId, PageRequest.of(1 - 1, AppConstant.PAGE_SIZE))
				.map(FavouriteMapper::map);
	}
	
	@Override
	public Page<FavouriteDto> findAllByCustomerId(final Integer customerId, final int offset, final int size) {
		return this.favouriteRepository.findAllByCustomerId(customerId, PageRequest.of(offset - 1, size))
				.map(FavouriteMapper::map);
	}
	
	@Transactional
	@Override
	public Boolean deleteById(final FavouriteId favouriteId) {
		this.favouriteRepository.deleteById(favouriteId);
		return !this.favouriteRepository.existsById(favouriteId);
	}
	
	
	
}













