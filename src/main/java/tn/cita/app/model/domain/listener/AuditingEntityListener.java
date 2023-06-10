package tn.cita.app.model.domain.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tn.cita.app.model.domain.entity.AbstractAuditingMappedEntity;

import java.time.Instant;
import java.util.UUID;

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



