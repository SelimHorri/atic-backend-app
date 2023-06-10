package tn.cita.app.model.domain.listener;

import jakarta.persistence.PrePersist;
import tn.cita.app.model.domain.entity.Favourite;

import java.time.LocalDateTime;

public class FavouriteEntityListener {
	
	@PrePersist
	public void preCreate(final Favourite favourite) {
		favourite.setFavouriteDate(LocalDateTime.now());
	}
	
}



