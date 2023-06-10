package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.cita.app.model.domain.entity.Location;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	
	Optional<Location> findByIdentifier(final String identifier);
	
}



