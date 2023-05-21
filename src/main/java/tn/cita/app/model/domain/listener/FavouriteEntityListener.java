package tn.cita.app.model.domain.listener;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;

import tn.cita.app.model.domain.entity.Favourite;

public class FavouriteEntityListener {
	
	@PrePersist
	public void preCreate(final Favourite favourite) {
		favourite.setFavouriteDate(LocalDateTime.now());
	}
	
}





