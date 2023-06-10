package tn.cita.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.cita.app.model.domain.entity.ServiceDetail;

import java.util.List;
import java.util.Optional;

public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, Integer> {
	
	Optional<ServiceDetail> findByIdentifier(final String identifier);
	List<ServiceDetail> findAllByCategoryId(final Integer categoryId);
	Page<ServiceDetail> findAllByCategoryId(final Integer categoryId, final Pageable pageable);
	List<ServiceDetail> findAllByCategorySaloonId(final Integer saloonId);
	
}



