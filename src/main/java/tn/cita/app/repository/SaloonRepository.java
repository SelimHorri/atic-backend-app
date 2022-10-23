package tn.cita.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Saloon;

public interface SaloonRepository extends JpaRepository<Saloon, Integer> {
	
	Optional<Saloon> findByIdentifier(final String identifier);
	Optional<Saloon> findByTaxRef(final String texRef);
	List<Saloon> findAllByCode(final String code);
	Page<Saloon> findAllByCode(final String code, final Pageable pageable);
	List<Saloon> findAllByLocationStateIgnoringCase(final String state);
	Page<Saloon> findAllByLocationStateIgnoringCase(final String state, final Pageable pageable);
	
}










