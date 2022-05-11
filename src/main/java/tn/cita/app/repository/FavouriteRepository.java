package tn.cita.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Favourite;
import tn.cita.app.domain.id.FavouriteId;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
	
	List<Favourite> findAllByCustomerId(final Integer customerId);
	
}








