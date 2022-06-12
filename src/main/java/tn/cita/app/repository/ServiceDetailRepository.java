package tn.cita.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.ServiceDetail;

public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, Integer> {
	
	Page<ServiceDetail> findAllByCategoryId(final Integer categoryId, final Pageable pageable);
	Page<ServiceDetail> findAllByCategorySaloonId(final Integer saloonId, final Pageable pageable);
	
}









