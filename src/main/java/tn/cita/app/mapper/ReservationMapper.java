package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Customer;
import tn.cita.app.domain.entity.Reservation;
import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.SaloonDto;

public interface ReservationMapper {
	
	public static ReservationDto map(@NotNull final Reservation reservation) {
		return ReservationDto.builder()
				.id(reservation.getId())
				.code(reservation.getCode())
				.description(reservation.getDescription())
				.startDate(reservation.getStartDate())
				.cancelDate(reservation.getCancelDate())
				.status(reservation.getStatus())
				.customerDto(
					CustomerDto.builder()
						.id(reservation.getCustomer().getId())
						.firstname(reservation.getCustomer().getFirstname())
						.lastname(reservation.getCustomer().getLastname())
						.email(reservation.getCustomer().getEmail())
						.phone(reservation.getCustomer().getPhone())
						.birthdate(reservation.getCustomer().getBirthdate())
						.build())
				.saloonDto(
					SaloonDto.builder()
						.id(reservation.getSaloon().getId())
						.code(reservation.getSaloon().getCode())
						.name(reservation.getSaloon().getName())
						.isPrimary(reservation.getSaloon().getIsPrimary())
						.openingDate(reservation.getSaloon().getOpeningDate())
						.fullAdr(reservation.getSaloon().getFullAdr())
						.iframeGoogleMap(reservation.getSaloon().getIframeGoogleMap())
						.email(reservation.getSaloon().getEmail())
						.build())
				.build();
	}
	
	public static Reservation map(@NotNull final ReservationDto reservationDto) {
		return Reservation.builder()
				.id(reservationDto.getId())
				.code(reservationDto.getCode())
				.description(reservationDto.getDescription())
				.startDate(reservationDto.getStartDate())
				.cancelDate(reservationDto.getCancelDate())
				.status(reservationDto.getStatus())
				.customer(
					Customer.builder()
						.id(reservationDto.getCustomerDto().getId())
						.firstname(reservationDto.getCustomerDto().getFirstname())
						.lastname(reservationDto.getCustomerDto().getLastname())
						.email(reservationDto.getCustomerDto().getEmail())
						.phone(reservationDto.getCustomerDto().getPhone())
						.birthdate(reservationDto.getCustomerDto().getBirthdate())
						.build())
				.saloon(
					Saloon.builder()
						.id(reservationDto.getSaloonDto().getId())
						.code(reservationDto.getSaloonDto().getCode())
						.name(reservationDto.getSaloonDto().getName())
						.isPrimary(reservationDto.getSaloonDto().getIsPrimary())
						.openingDate(reservationDto.getSaloonDto().getOpeningDate())
						.fullAdr(reservationDto.getSaloonDto().getFullAdr())
						.iframeGoogleMap(reservationDto.getSaloonDto().getIframeGoogleMap())
						.email(reservationDto.getSaloonDto().getEmail())
						.build())
				.build();
	}
	
	
	
}














