package tn.cita.app.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.config.annotation.LocalDateCustomFormat;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {
	"userImage", "manager", "workers", "credential", "saloon", "ratings"
})
@SuperBuilder
public class Employee extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String lastname;
	
	@Email(message = "Input must be in email format")
	private String email;
	
	@Size(message = "Input must be in phone format", min = 8, max = 12)
	@Column(precision = 8)
	private String phone;
	
	@LocalDateCustomFormat
	private LocalDate birthdate;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_image_id", referencedColumnName = "id")
	private UserImage userImage;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = true)
	private Employee manager;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manager")
	private Set<Employee> workers;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "credential_id", referencedColumnName = "id")
	private Credential credential;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saloon_id", referencedColumnName = "id")
	private Saloon saloon;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
	private Set<Rating> ratings;
	
}













