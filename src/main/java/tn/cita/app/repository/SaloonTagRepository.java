package tn.cita.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.model.domain.entity.SaloonTag;
import tn.cita.app.model.domain.id.SaloonTagId;

public interface SaloonTagRepository extends JpaRepository<SaloonTag, SaloonTagId> {
	
	List<SaloonTag> findAllBySaloonId(final Integer saloonId);
	
}





