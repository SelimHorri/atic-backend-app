package tn.cita.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.cita.app.model.domain.entity.OrderedDetail;
import tn.cita.app.model.domain.id.OrderedDetailId;
import tn.cita.app.model.dto.request.OrderedDetailRequest;

import java.util.List;
import java.util.Optional;

public interface OrderedDetailRepository extends JpaRepository<OrderedDetail, OrderedDetailId> {
	
	Optional<OrderedDetail> findByIdentifier(final String identifier);
	List<OrderedDetail> findAllByReservationId(final Integer reservationId);
	Page<OrderedDetail> findAllByReservationId(final Integer reservationId, final Pageable pageable);
	Page<OrderedDetail> findAllByServiceDetailId(final Integer serviceDetailId, final Pageable pageable);
	
	@Modifying
	@Query(name = "int.saveOrderedDetail", nativeQuery = true)
	int saveOrderedDetail(@Param("orderedDetail") final OrderedDetailRequest orderedDetail);
	
}



