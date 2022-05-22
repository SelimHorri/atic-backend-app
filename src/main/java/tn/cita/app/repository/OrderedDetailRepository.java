package tn.cita.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.cita.app.domain.entity.OrderedDetail;
import tn.cita.app.domain.id.OrderedDetailId;
import tn.cita.app.dto.request.OrderedDetailRequest;

public interface OrderedDetailRepository extends JpaRepository<OrderedDetail, OrderedDetailId> {
	
	List<OrderedDetail> findAllByReservationId(final Integer reservationId);
	List<OrderedDetail> findAllByServiceDetailId(final Integer serviceDetailId);
	
	@Modifying
	@Query(name = "OrderedDetail.saveOrderedDetail", nativeQuery = true)
	int saveOrderedDetail(@Param("orderedDetail") final OrderedDetailRequest orderedDetail);
	
}









