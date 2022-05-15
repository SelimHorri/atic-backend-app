package tn.cita.app.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Saloon;

public interface SaloonRepository extends JpaRepository<Saloon, Integer> {
	
	List<Saloon> findAllByCode(final String code);
	List<Saloon> findAllByLocationStateIgnoringCase(final String state, final Pageable pageable);
	
}










