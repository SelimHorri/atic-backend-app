package tn.cita.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.model.domain.entity.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
	
	Optional<UserImage> findByIdentifier(final String identifier);
	
}









