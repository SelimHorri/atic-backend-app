package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Customer;
import tn.cita.app.domain.entity.Favourite;
import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.dto.FavouriteDto;

public interface FavouriteMapper {
	
	public static FavouriteDto map(@NotNull final Favourite favourite) {
		return FavouriteDto.builder()
				.customerId(favourite.getCustomerId())
				.saloonId(favourite.getSaloonId())
				.favouriteDate(favourite.getFavouriteDate())
				.customerId(favourite.getCustomerId())
				.saloonId(favourite.getSaloonId())
				.build();
	}
	
	public static Favourite map(@NotNull final FavouriteDto favouriteDto) {
		return Favourite.builder()
				.customerId(favouriteDto.getCustomerId())
				.saloonId(favouriteDto.getSaloonId())
				.favouriteDate(favouriteDto.getFavouriteDate())
				.customer(
					Customer.builder()
						.id(favouriteDto.getCustomerId())
						.build())
				.saloon(
					Saloon.builder()
						.id(favouriteDto.getSaloonId())
						.build())
				.build();
	}
	
	
	
}














