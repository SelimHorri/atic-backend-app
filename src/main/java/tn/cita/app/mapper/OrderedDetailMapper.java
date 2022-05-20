package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.OrderedDetail;
import tn.cita.app.domain.entity.Reservation;
import tn.cita.app.domain.entity.ServiceDetail;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.ServiceDetailDto;

public interface OrderedDetailMapper {
	
	public static OrderedDetailDto map(@NotNull final OrderedDetail orderedDetail) {
		return OrderedDetailDto.builder()
				.reservationId(orderedDetail.getReservationId())
				.serviceDetailId(orderedDetail.getServiceDetailId())
				.orderedDate(orderedDetail.getOrderedDate())
				.reservationDto(
					ReservationDto.builder()
						.id(orderedDetail.getReservation().getId())
						.code(orderedDetail.getReservation().getCode())
						.description(orderedDetail.getReservation().getDescription())
						.startDate(orderedDetail.getReservation().getStartDate())
						.cancelDate(orderedDetail.getReservation().getCancelDate())
						.status(orderedDetail.getReservation().getStatus())
						.build())
				.serviceDetailDto(
					ServiceDetailDto.builder()
						.id(orderedDetail.getServiceDetail().getId())
						.name(orderedDetail.getServiceDetail().getName())
						.description(orderedDetail.getServiceDetail().getDescription())
						.isAvailable(orderedDetail.getServiceDetail().getIsAvailable())
						.duration(orderedDetail.getServiceDetail().getDuration())
						.priceUnit(orderedDetail.getServiceDetail().getPriceUnit())
						.build())
				.build();
	}
	
	public static OrderedDetail map(@NotNull final OrderedDetailDto orderedDetailDto) {
		return OrderedDetail.builder()
				.reservationId(orderedDetailDto.getReservationId())
				.serviceDetailId(orderedDetailDto.getServiceDetailId())
				.orderedDate(orderedDetailDto.getOrderedDate())
				.reservation(
						Reservation.builder()
						.id(orderedDetailDto.getReservationDto().getId())
						.code(orderedDetailDto.getReservationDto().getCode())
						.description(orderedDetailDto.getReservationDto().getDescription())
						.startDate(orderedDetailDto.getReservationDto().getStartDate())
						.cancelDate(orderedDetailDto.getReservationDto().getCancelDate())
						.status(orderedDetailDto.getReservationDto().getStatus())
						.build())
				.serviceDetail(
						ServiceDetail.builder()
						.id(orderedDetailDto.getServiceDetailDto().getId())
						.name(orderedDetailDto.getServiceDetailDto().getName())
						.description(orderedDetailDto.getServiceDetailDto().getDescription())
						.isAvailable(orderedDetailDto.getServiceDetailDto().getIsAvailable())
						.duration(orderedDetailDto.getServiceDetailDto().getDuration())
						.priceUnit(orderedDetailDto.getServiceDetailDto().getPriceUnit())
						.build())
				.build();
	}
	
	
	
}














