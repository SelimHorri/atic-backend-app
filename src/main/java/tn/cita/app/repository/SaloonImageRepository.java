package tn.cita.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.model.domain.entity.SaloonImage;

public interface SaloonImageRepository extends JpaRepository<SaloonImage, Integer> {
	
	Optional<SaloonImage> findByIdentifier(final String identifier);
	
}



