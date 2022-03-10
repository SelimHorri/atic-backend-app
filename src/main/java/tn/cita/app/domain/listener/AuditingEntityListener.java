package tn.cita.app.domain.listener;

import java.time.Instant;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tn.cita.app.domain.entity.AbstractAuditingMappedEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuditingEntityListener {
	
	@PrePersist
	void preCreate(final AbstractAuditingMappedEntity auditable) {
		final var now = Instant.now();
		auditable.setCreatedAt(now);
		auditable.setUpdatedAt(now);
	}
	
	@PreUpdate
	void preUpdate(final AbstractAuditingMappedEntity auditable) {
		final var now = Instant.now();
		auditable.setUpdatedAt(now);
	}
	
	
	
}














