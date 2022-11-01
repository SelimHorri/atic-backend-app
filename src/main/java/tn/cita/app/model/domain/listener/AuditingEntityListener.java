package tn.cita.app.model.domain.listener;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tn.cita.app.model.domain.entity.AbstractAuditingMappedEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuditingEntityListener {
	
	@PrePersist
	public void preCreate(final AbstractAuditingMappedEntity auditable) {
		
		auditable.setIdentifier(UUID.randomUUID().toString());
		
		final var now = Instant.now();
		auditable.setCreatedAt(now);
		auditable.setUpdatedAt(now);
	}
	
	@PreUpdate
	public void preUpdate(final AbstractAuditingMappedEntity auditable) {
		final var now = Instant.now();
		auditable.setUpdatedAt(now);
	}
	
	
	
}














