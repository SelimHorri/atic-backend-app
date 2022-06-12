package tn.cita.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	List<Category> findAllBySaloonId(final Integer saloonId);
	
}








