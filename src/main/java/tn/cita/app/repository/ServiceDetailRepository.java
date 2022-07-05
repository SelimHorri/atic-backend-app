package tn.cita.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.ServiceDetail;

public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, Integer> {
	
	List<ServiceDetail> findAllByCategoryId(final Integer categoryId);
	Page<ServiceDetail> findAllByCategoryId(final Integer categoryId, final Pageable pageable);
	List<ServiceDetail> findAllByCategorySaloonId(final Integer saloonId);
	
}









