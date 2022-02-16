package tn.cita.app.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.config.annotation.LocalDateTimeCustomFormat;
import tn.cita.app.domain.ReservationStatus;

@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Reservation extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String code;
	
	@Column(nullable = true)
	private String description;
	
	@LocalDateTimeCustomFormat
	@Column(name = "start_date", nullable = true)
	private LocalDateTime startDate;
	
	@Transient
	@LocalDateTimeCustomFormat
	// @Column(name = "cancelled_date", nullable = true)
	private LocalDateTime cancelledDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ReservationStatus reservationStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservation")
	private Set<OrderedDetail> orderedDetails;
	
}















