package tn.cita.app.model.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import tn.cita.app.constant.AppConstants;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Customer extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 4605663219739969043L;
	
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
	
	@Column(name = "facebook_url", nullable = true)
	private String facebookUrl;
	
	@Column(name = "instagram_url", nullable = true)
	private String instagramUrl;
	
	@Column(name = "linkedin_url", nullable = true)
	private String linkedinUrl;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_image_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private UserImage userImage;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credential_id", referencedColumnName = "id", nullable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Credential credential;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Rating> ratings;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Favourite> favourites;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Reservation> reservations;
	
}




