package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.cita.app.model.domain.entity.SaloonImage;

import java.util.Optional;

public interface SaloonImageRepository extends JpaRepository<SaloonImage, Integer> {
	
	Optional<SaloonImage> findByIdentifier(final String identifier);
	
}



