package tn.cita.app.model.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.domain.UserRating;
import tn.cita.app.model.domain.id.RatingId;
import tn.cita.app.model.domain.listener.RatingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
@EntityListeners(RatingEntityListener.class)
@IdClass(RatingId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Rating extends AbstractAuditingMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1391301889230563789L;
	
	@Id
	@Column(name = "worker_id", nullable = false, insertable = false, updatable = false)
	private Integer workerId;
	
	@Id
	@Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
	private Integer customerId;
	
	@Id
	@Column(name = "rate_date", nullable = false, insertable = false, updatable = false)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime rateDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "rate", nullable = false)
	private UserRating rate;
	
	@Column(nullable = true)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false, updatable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Employee worker;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false, updatable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Customer customer;
	
}




