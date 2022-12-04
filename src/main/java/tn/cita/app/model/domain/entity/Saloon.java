package tn.cita.app.model.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tn.cita.app.constant.AppConstants;

@Entity
@Table(name = "saloons")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Saloon extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
	@JsonFormat(pattern = AppConstants.LOCAL_DATE_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_FORMAT)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
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













