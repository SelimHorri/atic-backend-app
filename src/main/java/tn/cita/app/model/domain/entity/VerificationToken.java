package tn.cita.app.model.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.domain.listener.VerificationTokenEntityListener;

@Entity
@Table(name = "verification_tokens")
@EntityListeners(VerificationTokenEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class VerificationToken extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = -4708926740139772505L;
	
	@Column(unique = true, nullable = false)
	private String token;
	
	@Column(name = "expire_date")
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime expireDate;
	
	@ManyToOne
	@JoinColumn(name = "credential_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Credential credential;
	
}






