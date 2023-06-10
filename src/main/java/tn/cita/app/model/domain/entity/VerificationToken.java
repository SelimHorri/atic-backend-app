package tn.cita.app.model.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.domain.listener.VerificationTokenEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

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




