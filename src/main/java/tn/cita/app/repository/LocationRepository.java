package tn.cita.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.model.domain.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	
	Optional<Location> findByIdentifier(final String identifier);
	
}





