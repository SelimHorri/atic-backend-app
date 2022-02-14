package tn.cita.app.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.config.annotation.LocalDateTimeCustomFormat;

@Entity
@Table(name = "verification_tokens")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"credential"})
@SuperBuilder
public class VerificationToken extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(unique = true, nullable = false)
	private String token;
	
	@LocalDateTimeCustomFormat
	@Column(name = "expire_date")
	private LocalDateTime expireDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "credential_id", referencedColumnName = "id")
	private Credential credential;
	
}











