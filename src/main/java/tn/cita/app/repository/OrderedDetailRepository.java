package tn.cita.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.OrderedDetail;
import tn.cita.app.domain.id.OrderedDetailId;

public interface OrderedDetailRepository extends JpaRepository<OrderedDetail, OrderedDetailId> {
	
	List<OrderedDetail> findAllByReservationId(final Integer reservationId);
	List<OrderedDetail> findAllByServiceDetailId(final Integer serviceDetailId);
	
}









