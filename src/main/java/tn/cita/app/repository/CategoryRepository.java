package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.cita.app.model.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Optional<Category> findByIdentifier(final String identifier);
	List<Category> findAllBySaloonId(final Integer saloonId);
	
}


