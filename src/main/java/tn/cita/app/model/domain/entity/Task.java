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
import tn.cita.app.model.domain.id.TaskId;
import tn.cita.app.model.domain.listener.TaskEntityListener;

@Entity
@Table(name = "tasks")
@EntityListeners(TaskEntityListener.class)
@IdClass(TaskId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Task extends AbstractAuditingMappedEntity implements Serializable {
	
	private static final long serialVersionUID = -4019147962632495083L;
	
	@Id
	@Column(name = "worker_id", nullable = false, insertable = false, updatable = false)
	private Integer workerId;
	
	@Id
	@Column(name = "reservation_id", nullable = false, insertable = false, updatable = false)
	private Integer reservationId;
	
	@Column(name = "task_date", nullable = false)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime taskDate;
	
	@Column(name = "start_date", nullable = false)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime startDate;
	
	@Column(name = "end_date", nullable = false)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime endDate;
	
	@Column(name = "worker_description", nullable = true)
	private String workerDescription;
	
	@Column(name = "manager_description", nullable = true)
	private String managerDescription;
	
	@ManyToOne
	@JoinColumn(name = "worker_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Employee worker;
	
	@ManyToOne
	@JoinColumn(name = "reservation_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Reservation reservation;
	
}




