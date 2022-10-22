package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Customer;
import tn.cita.app.domain.entity.Favourite;
import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.FavouriteDto;
import tn.cita.app.dto.SaloonDto;

public interface FavouriteMapper {
	
	public static FavouriteDto map(@NotNull final Favourite favourite) {
		return FavouriteDto.builder()
				.customerId(favourite.getCustomerId())
				.saloonId(favourite.getSaloonId())
				.favouriteDate(favourite.getFavouriteDate())
				.identifier(favourite.getIdentifier())
				.customerDto(
					CustomerDto.builder()
						.id(favourite.getCustomer().getId())
						.identifier(favourite.getCustomer().getIdentifier())
						.ssn(favourite.getCustomer().getSsn())
						.firstname(favourite.getCustomer().getFirstname())
						.lastname(favourite.getCustomer().getLastname())
						.email(favourite.getCustomer().getEmail())
						.phone(favourite.getCustomer().getPhone())
						.birthdate(favourite.getCustomer().getBirthdate())
						.build())
				.saloonDto(
					SaloonDto.builder()
						.id(favourite.getSaloon().getId())
						.identifier(favourite.getSaloon().getIdentifier())
						.code(favourite.getSaloon().getCode())
						.name(favourite.getSaloon().getName())
						.isPrimary(favourite.getSaloon().getIsPrimary())
						.openingDate(favourite.getSaloon().getOpeningDate())
						.fullAdr(favourite.getSaloon().getFullAdr())
						.email(favourite.getSaloon().getEmail())
						.build())
				.build();
	}
	
	public static Favourite map(@NotNull final FavouriteDto favouriteDto) {
		return Favourite.builder()
				.customerId(favouriteDto.getCustomerId())
				.saloonId(favouriteDto.getSaloonId())
				.favouriteDate(favouriteDto.getFavouriteDate())
				.identifier(favouriteDto.getIdentifier())
				.customer(
					Customer.builder()
						.id(favouriteDto.getCustomerDto().getId())
						.identifier(favouriteDto.getCustomerDto().getIdentifier())
						.ssn(favouriteDto.getCustomerDto().getSsn())
						.firstname(favouriteDto.getCustomerDto().getFirstname())
						.lastname(favouriteDto.getCustomerDto().getLastname())
						.email(favouriteDto.getCustomerDto().getEmail())
						.phone(favouriteDto.getCustomerDto().getPhone())
						.birthdate(favouriteDto.getCustomerDto().getBirthdate())
						.build())
				.saloon(
					Saloon.builder()
						.id(favouriteDto.getSaloonDto().getId())
						.identifier(favouriteDto.getSaloonDto().getIdentifier())
						.code(favouriteDto.getSaloonDto().getCode())
						.name(favouriteDto.getSaloonDto().getName())
						.isPrimary(favouriteDto.getSaloonDto().getIsPrimary())
						.openingDate(favouriteDto.getSaloonDto().getOpeningDate())
						.fullAdr(favouriteDto.getSaloonDto().getFullAdr())
						.email(favouriteDto.getSaloonDto().getEmail())
						.build())
				.build();
	}
	
	
	
}














