package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.SaloonDto;
import tn.cita.app.exception.wrapper.SaloonNotFoundException;
import tn.cita.app.mapper.SaloonMapper;
import tn.cita.app.repository.SaloonRepository;
import tn.cita.app.service.SaloonService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SaloonServiceImpl implements SaloonService {
	
	private final SaloonRepository saloonRepository;
	
	@Override
	public SaloonDto findById(final Integer id) {
		return this.saloonRepository.findById(id)
				.map(SaloonMapper::map)
				.orElseThrow(() -> new SaloonNotFoundException(String
						.format("Saloon with id: %d not found", id)));
	}
	
	@Override
	public List<SaloonDto> findAllByCode(final String code) {
		return this.saloonRepository.findAllByCode(code)
				.stream()
					.map(SaloonMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}














