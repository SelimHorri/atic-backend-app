package tn.cita.app.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.config.annotation.LocalDateTimeCustomFormat;
import tn.cita.app.domain.id.SaloonTagId;

@Entity
@Table(name = "saloon_tags")
@IdClass(SaloonTagId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SaloonTag extends AbstractAuditingMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "saloon_id", nullable = false, insertable = false, updatable = false)
	private Integer saloonId;
	
	@Id
	@Column(name = "tag_id", nullable = false, insertable = false, updatable = false)
	private Integer tagId;
	
	@LocalDateTimeCustomFormat
	@Column(name = "tagged_date", nullable = false)
	private LocalDateTime taggedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saloon_id", referencedColumnName = "id")
	private Saloon saloon;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id", referencedColumnName = "id")
	private Tag tag;
	
}















