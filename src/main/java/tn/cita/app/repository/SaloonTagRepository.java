package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.cita.app.model.domain.entity.SaloonTag;
import tn.cita.app.model.domain.id.SaloonTagId;

import java.util.List;

public interface SaloonTagRepository extends JpaRepository<SaloonTag, SaloonTagId> {
	
	List<SaloonTag> findAllBySaloonId(final Integer saloonId);
	
}



