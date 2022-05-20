package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Customer;
import tn.cita.app.domain.entity.Reservation;
import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.dto.ReservationDto;

public interface ReservationMapper {
	
	public static ReservationDto map(@NotNull final Reservation reservation) {
		return ReservationDto.builder()
				.id(reservation.getId())
				.code(reservation.getCode())
				.description(reservation.getDescription())
				.startDate(reservation.getStartDate())
				.cancelDate(reservation.getCancelDate())
				.status(reservation.getStatus())
				.customerId(reservation.getCustomer().getId())
				.saloonId(reservation.getSaloon().getId())
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
						.id(reservationDto.getCustomerId())
						.build())
				.saloon(
					Saloon.builder()
						.id(reservationDto.getSaloonId())
						.build())
				.build();
	}
	
	
	
}














