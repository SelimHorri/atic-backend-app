package tn.cita.app.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.domain.UserRating;
import tn.cita.app.domain.id.RatingId;
import tn.cita.app.domain.listener.RatingEntityListener;

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
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "employee_id", nullable = false, insertable = false, updatable = false)
	private Integer workerId;
	
	@Id
	@Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
	private Integer customerId;
	
	@Id
	@Column(name = "rate_date", nullable = false, insertable = false, updatable = false)
	@JsonFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime rateDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "rate", nullable = false)
	private UserRating rate;
	
	@Column(nullable = true)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false, updatable = false)
	@EqualsAndHashCode.Exclude
	private Employee worker;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false, updatable = false)
	@EqualsAndHashCode.Exclude
	private Customer customer;
	
}











