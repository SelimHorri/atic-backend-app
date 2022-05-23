package tn.cita.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Saloon;

public interface SaloonRepository extends JpaRepository<Saloon, Integer> {
	
	Page<Saloon> findAllByCode(final String code, final Pageable pageable);
	Page<Saloon> findAllByLocationStateIgnoringCase(final String state, final Pageable pageable);
	
}










