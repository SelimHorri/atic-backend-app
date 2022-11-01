package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.model.domain.entity.OrderedDetail;
import tn.cita.app.model.domain.entity.Reservation;
import tn.cita.app.model.domain.entity.ServiceDetail;
import tn.cita.app.model.dto.OrderedDetailDto;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.ServiceDetailDto;

public interface OrderedDetailMapper {
	
	public static OrderedDetailDto map(@NotNull final OrderedDetail orderedDetail) {
		return OrderedDetailDto.builder()
				.reservationId(orderedDetail.getReservationId())
				.serviceDetailId(orderedDetail.getServiceDetailId())
				.orderedDate(orderedDetail.getOrderedDate())
				.identifier(orderedDetail.getIdentifier())
				.reservationDto(
					ReservationDto.builder()
						.id(orderedDetail.getReservation().getId())
						.identifier(orderedDetail.getReservation().getIdentifier())
						.code(orderedDetail.getReservation().getCode())
						.description(orderedDetail.getReservation().getDescription())
						.startDate(orderedDetail.getReservation().getStartDate())
						.cancelDate(orderedDetail.getReservation().getCancelDate())
						.completeDate(orderedDetail.getReservation().getCompleteDate())
						.status(orderedDetail.getReservation().getStatus())
						.build())
				.serviceDetailDto(
					ServiceDetailDto.builder()
						.id(orderedDetail.getServiceDetail().getId())
						.identifier(orderedDetail.getServiceDetail().getIdentifier())
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
				.identifier(orderedDetailDto.getIdentifier())
				.reservation(
						Reservation.builder()
						.id(orderedDetailDto.getReservationDto().getId())
						.identifier(orderedDetailDto.getReservationDto().getIdentifier())
						.code(orderedDetailDto.getReservationDto().getCode())
						.description(orderedDetailDto.getReservationDto().getDescription())
						.startDate(orderedDetailDto.getReservationDto().getStartDate())
						.cancelDate(orderedDetailDto.getReservationDto().getCancelDate())
						.completeDate(orderedDetailDto.getReservationDto().getCompleteDate())
						.status(orderedDetailDto.getReservationDto().getStatus())
						.build())
				.serviceDetail(
						ServiceDetail.builder()
						.id(orderedDetailDto.getServiceDetailDto().getId())
						.identifier(orderedDetailDto.getServiceDetailDto().getIdentifier())
						.name(orderedDetailDto.getServiceDetailDto().getName())
						.description(orderedDetailDto.getServiceDetailDto().getDescription())
						.isAvailable(orderedDetailDto.getServiceDetailDto().getIsAvailable())
						.duration(orderedDetailDto.getServiceDetailDto().getDuration())
						.priceUnit(orderedDetailDto.getServiceDetailDto().getPriceUnit())
						.build())
				.build();
	}
	
	
	
}














