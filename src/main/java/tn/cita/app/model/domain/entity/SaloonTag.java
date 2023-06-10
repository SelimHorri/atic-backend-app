package tn.cita.app.model.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.domain.id.SaloonTagId;
import tn.cita.app.model.domain.listener.SaloonTagEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "saloon_tags")
@EntityListeners(SaloonTagEntityListener.class)
@IdClass(SaloonTagId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SaloonTag extends AbstractAuditingMappedEntity implements Serializable {
	
	private static final long serialVersionUID = -5193869326657338687L;

	@Id
	@Column(name = "saloon_id", nullable = false, insertable = false, updatable = false)
	private Integer saloonId;
	
	@Id
	@Column(name = "tag_id", nullable = false, insertable = false, updatable = false)
	private Integer tagId;
	
	@Column(name = "tagged_date", nullable = false)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime taggedDate;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "saloon_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Saloon saloon;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "tag_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Tag tag;
	
}



