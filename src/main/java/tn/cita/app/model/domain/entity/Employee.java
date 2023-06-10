package tn.cita.app.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import tn.cita.app.constant.AppConstants;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Employee extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 7575884590373676497L;
	
	@Column(precision = 8, nullable = true)
	private String ssn;
	
	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column(name = "is_male", nullable = true)
	private Boolean isMale;
	
	@Column(nullable = false)
	private String email;
	
	@Column(precision = 8, nullable = true)
	private String phone;
	
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_FORMAT)
	private LocalDate birthdate;
	
	@JsonFormat(pattern = AppConstants.LOCAL_DATE_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_FORMAT)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate hiredate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_image_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private UserImage userImage;
	
	@ManyToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Employee manager;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manager")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Employee> workers;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credential_id", referencedColumnName = "id", nullable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Credential credential;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "saloon_id", referencedColumnName = "id", nullable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Saloon saloon;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "worker")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Rating> ratings;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "worker")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Task> tasks;
	
}




