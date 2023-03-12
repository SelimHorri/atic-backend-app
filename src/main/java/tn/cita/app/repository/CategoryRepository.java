package tn.cita.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.model.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Optional<Category> findByIdentifier(final String identifier);
	List<Category> findAllBySaloonId(final Integer saloonId);
	
}





