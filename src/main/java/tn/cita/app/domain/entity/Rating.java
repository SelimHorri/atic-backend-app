package tn.cita.app.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import tn.cita.app.domain.UserRating;
import tn.cita.app.domain.id.RatingId;

@Entity
@Table(name = "ratings")
@IdClass(RatingId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Rating extends AbstractAuditingMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "employee_id", nullable = false, insertable = false, updatable = false)
	private Integer employeeId;
	
	@Id
	@Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
	private Integer customerId;
	
	@Id
	@Column(name = "rate_date", nullable = false, insertable = false, updatable = false)
	@LocalDateTimeCustomFormat
	private LocalDateTime rateDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "rate")
	private UserRating rate;
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false, updatable = false)
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false, updatable = false)
	private Customer customer;
	
}











