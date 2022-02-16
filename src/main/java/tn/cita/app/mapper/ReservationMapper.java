package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Customer;
import tn.cita.app.domain.entity.Reservation;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.ReservationDto;

public interface ReservationMapper {
	
	public static ReservationDto map(@NotNull final Reservation reservation) {
		return ReservationDto.builder()
				.id(reservation.getId())
				.code(reservation.getCode())
				.description(reservation.getDescription())
				.startDate(reservation.getStartDate())
				.cancelledDate(reservation.getCancelledDate())
				.reservationStatus(reservation.getReservationStatus())
				.customerDto(
					CustomerDto.builder()
						.id(reservation.getCustomer().getId())
						.firstname(reservation.getCustomer().getFirstname())
						.lastname(reservation.getCustomer().getLastname())
						.email(reservation.getCustomer().getEmail())
						.phone(reservation.getCustomer().getPhone())
						.birthdate(reservation.getCustomer().getBirthdate())
						.build())
				.build();
	}
	
	public static Reservation map(@NotNull final ReservationDto reservationDto) {
		return Reservation.builder()
				.id(reservationDto.getId())
				.code(reservationDto.getCode())
				.description(reservationDto.getDescription())
				.startDate(reservationDto.getStartDate())
				.cancelledDate(reservationDto.getCancelledDate())
				.reservationStatus(reservationDto.getReservationStatus())
				.customer(
					Customer.builder()
						.id(reservationDto.getCustomerDto().getId())
						.firstname(reservationDto.getCustomerDto().getFirstname())
						.lastname(reservationDto.getCustomerDto().getLastname())
						.email(reservationDto.getCustomerDto().getEmail())
						.phone(reservationDto.getCustomerDto().getPhone())
						.birthdate(reservationDto.getCustomerDto().getBirthdate())
						.build())
				.build();
	}
	
	
	
}













