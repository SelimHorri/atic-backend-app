package tn.cita.app.domain.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;

import tn.cita.app.domain.entity.Favourite;

public class FavouriteEntityListener {
	
	@PrePersist
	public void preCreate(final Favourite favourite) {
		favourite.setFavouriteDate(LocalDateTime.now());
	}
	
	
	
}












