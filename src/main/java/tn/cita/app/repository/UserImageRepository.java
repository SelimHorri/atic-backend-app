package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.cita.app.model.domain.entity.UserImage;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
	
	Optional<UserImage> findByIdentifier(final String identifier);
	
}


