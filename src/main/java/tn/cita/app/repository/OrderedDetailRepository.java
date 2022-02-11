package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.OrderedDetail;
import tn.cita.app.domain.id.OrderedDetailId;

public interface OrderedDetailRepository extends JpaRepository<OrderedDetail, OrderedDetailId> {
	
	
	
}










