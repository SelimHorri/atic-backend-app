package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.model.domain.entity.Customer;
import tn.cita.app.model.domain.entity.Reservation;
import tn.cita.app.model.domain.entity.Saloon;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.SaloonDto;

public interface ReservationMapper {
	
	public static ReservationDto map(@NotNull final Reservation reservation) {
		return ReservationDto.builder()
				.id(reservation.getId())
				.identifier(reservation.getIdentifier())
				.code(reservation.getCode())
				.description(reservation.getDescription())
				.startDate(reservation.getStartDate())
				.cancelDate(reservation.getCancelDate())
				.completeDate(reservation.getCompleteDate())
				.status(reservation.getStatus())
				.customerDto(
					CustomerDto.builder()
						.id(reservation.getCustomer().getId())
						.identifier(reservation.getCustomer().getIdentifier())
						.ssn(reservation.getCustomer().getSsn())
						.firstname(reservation.getCustomer().getFirstname())
						.lastname(reservation.getCustomer().getLastname())
						.isMale(reservation.getCustomer().getIsMale())
						.email(reservation.getCustomer().getEmail())
						.phone(reservation.getCustomer().getPhone())
						.birthdate(reservation.getCustomer().getBirthdate())
						.build())
				.saloonDto(
					SaloonDto.builder()
						.id(reservation.getSaloon().getId())
						.identifier(reservation.getSaloon().getIdentifier())
						.code(reservation.getSaloon().getCode())
						.taxRef(reservation.getSaloon().getTaxRef())
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
				.identifier(reservationDto.getIdentifier())
				.code(reservationDto.getCode())
				.description(reservationDto.getDescription())
				.startDate(reservationDto.getStartDate())
				.cancelDate(reservationDto.getCancelDate())
				.completeDate(reservationDto.getCompleteDate())
				.status(reservationDto.getStatus())
				.customer(
					Customer.builder()
						.id(reservationDto.getCustomerDto().getId())
						.identifier(reservationDto.getCustomerDto().getIdentifier())
						.ssn(reservationDto.getCustomerDto().getSsn())
						.firstname(reservationDto.getCustomerDto().getFirstname())
						.lastname(reservationDto.getCustomerDto().getLastname())
						.isMale(reservationDto.getCustomerDto().getIsMale())
						.email(reservationDto.getCustomerDto().getEmail())
						.phone(reservationDto.getCustomerDto().getPhone())
						.birthdate(reservationDto.getCustomerDto().getBirthdate())
						.build())
				.saloon(
					Saloon.builder()
						.id(reservationDto.getSaloonDto().getId())
						.identifier(reservationDto.getSaloonDto().getIdentifier())
						.code(reservationDto.getSaloonDto().getCode())
						.taxRef(reservationDto.getSaloonDto().getTaxRef())
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














