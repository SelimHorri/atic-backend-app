package tn.cita.app.model.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
import tn.cita.app.model.domain.id.OrderedDetailId;
import tn.cita.app.model.domain.listener.OrderedDetailEntityListener;

@Entity
@Table(name = "ordered_details")
@EntityListeners(OrderedDetailEntityListener.class)
@IdClass(OrderedDetailId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class OrderedDetail extends AbstractAuditingMappedEntity implements Serializable {
	
	private static final long serialVersionUID = -1384024973693896748L;
	
	@Id
	@Column(name = "reservation_id", nullable = false, insertable = false, updatable = false)
	private Integer reservationId;
	
	@Id
	@Column(name = "service_detail_id", nullable = false, insertable = false, updatable = false)
	private Integer serviceDetailId;
	
	@Column(name = "ordered_date", nullable = false)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime orderedDate;
	
	@ManyToOne
	@JoinColumn(name = "reservation_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Reservation reservation;
	
	@ManyToOne
	@JoinColumn(name = "service_detail_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private ServiceDetail serviceDetail;
	
}





