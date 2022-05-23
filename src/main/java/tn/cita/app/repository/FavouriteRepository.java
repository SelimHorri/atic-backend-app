package tn.cita.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Favourite;
import tn.cita.app.domain.id.FavouriteId;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
	
	Page<Favourite> findAllByCustomerId(final Integer customerId, final Pageable pageable);
	
}








