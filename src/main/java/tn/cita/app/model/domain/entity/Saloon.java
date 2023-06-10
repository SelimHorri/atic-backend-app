package tn.cita.app.model.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import tn.cita.app.constant.AppConstants;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "saloons")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Saloon extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 3345068914732889502L;
	
	@Column(nullable = false)
	private String code;
	
	// @TaxRefFormat
	@Column(name = "tax_ref", precision = 8, unique = true, nullable = false)
	private String taxRef;
	
	@Column(nullable = false)
	private String name;
	
	@Column(name = "is_primary", nullable = false)
	private Boolean isPrimary;
	
	@Column(name = "opening_date", nullable = true)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_FORMAT)
	private LocalDate openingDate;
	
	@Column(name = "full_adr", nullable = true)
	private String fullAdr;
	
	@Lob
	@Column(name = "iframe_google_map", columnDefinition = "TEXT", nullable = true)
	private String iframeGoogleMap;
	
	@Email(message = "Input must be in email format")
	@Column(nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Location location;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "saloon")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Employee> employees;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "saloon")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Favourite> favourites;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "saloon")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<SaloonImage> saloonImage;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "saloon")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<SaloonTag> saloonTags;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "saloon")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Category> categories;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "saloon")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Reservation> reservations;
	
}




