package tn.cita.app.model.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tn.cita.app.constant.AppConstants;

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
	
	@JsonFormat(pattern = AppConstants.LOCAL_DATE_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_FORMAT)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
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





