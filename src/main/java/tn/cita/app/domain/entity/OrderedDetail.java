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
import tn.cita.app.domain.id.OrderedDetailId;

@Entity
@Table(name = "ordered_details")
@IdClass(OrderedDetailId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"reservation", "serviceDetail"})
@SuperBuilder
public class OrderedDetail extends AbstractAuditingMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "reservation_id", nullable = false, insertable = false, updatable = false)
	private Integer reservationId;
	
	@Id
	@Column(name = "service_detail_id", nullable = false, insertable = false, updatable = false)
	private Integer serviceDetailId;
	
	@Id
	@Column(name = "ordered_date", nullable = false)
	@LocalDateTimeCustomFormat
	private LocalDateTime orderedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id", referencedColumnName = "id")
	private Reservation reservation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_detail_id", referencedColumnName = "id")
	private ServiceDetail serviceDetail;
	
}













