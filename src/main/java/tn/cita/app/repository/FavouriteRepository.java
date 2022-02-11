package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Favourite;
import tn.cita.app.domain.id.FavouriteId;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
	
	
	
}









