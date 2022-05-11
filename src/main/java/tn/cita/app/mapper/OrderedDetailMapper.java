package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.OrderedDetail;
import tn.cita.app.domain.entity.Reservation;
import tn.cita.app.domain.entity.ServiceDetail;
import tn.cita.app.dto.OrderedDetailDto;

public interface OrderedDetailMapper {
	
	public static OrderedDetailDto map(@NotNull final OrderedDetail orderedDetail) {
		return OrderedDetailDto.builder()
				.reservationId(orderedDetail.getReservationId())
				.serviceDetailId(orderedDetail.getServiceDetailId())
				.orderedDate(orderedDetail.getOrderedDate())
				.build();
	}
	
	public static OrderedDetail map(@NotNull final OrderedDetailDto orderedDetailDto) {
		return OrderedDetail.builder()
				.reservationId(orderedDetailDto.getReservationId())
				.serviceDetailId(orderedDetailDto.getServiceDetailId())
				.orderedDate(orderedDetailDto.getOrderedDate())
				.reservation(
						Reservation.builder()
						.id(orderedDetailDto.getReservationId())
						.build())
				.serviceDetail(
						ServiceDetail.builder()
						.id(orderedDetailDto.getServiceDetailId())
						.build())
				.build();
	}
	
	
	
}














