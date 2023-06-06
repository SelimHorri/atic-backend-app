package tn.cita.app.model.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.domain.ReservationStatus;
import tn.cita.app.model.domain.listener.ReservationEntityListener;

@Entity
@Table(name = "reservations")
@EntityListeners(ReservationEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Reservation extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = -3765103550348033742L;
	
	@Column(nullable = false, unique = true)
	private String code;
	
	@Column(nullable = true)
	private String description;
	
	@Column(name = "start_date", nullable = true)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime startDate;
	
	@Column(name = "cancel_date", nullable = true)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime cancelDate;
	
 	@Column(name = "complete_date", nullable = true)
 	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime completeDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ReservationStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Customer customer;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservation")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<OrderedDetail> orderedDetails;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservation")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Task> tasks;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "saloon_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Saloon saloon;
	
}




