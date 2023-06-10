package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.cita.app.model.domain.entity.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {
	
	Optional<Tag> findByIdentifier(final String identifier);
	
}


