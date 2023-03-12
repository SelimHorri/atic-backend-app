package tn.cita.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.model.domain.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
	
	Optional<Tag> findByIdentifier(final String identifier);
	
}




