package tn.cita.app.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Input firstname should not be blank")
	@Column(nullable = false)
	private String firstname;
	
	@NotBlank(message = "Input lastname should not be blank")
	@Column(nullable = false)
	private String lastname;
	
	@Email(message = "Input email should be in email format")
	@NotBlank(message = "Input email should not be blank")
	@Column(nullable = false)
	private String email;
	
	@Size(message = "Input phone should be in a phone number format", min = 8, max = 12)
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
	@EqualsAndHashCode.Exclude
	private UserImage userImage;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credential_id", referencedColumnName = "id", nullable = false)
	@EqualsAndHashCode.Exclude
	private Credential credential;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
	@EqualsAndHashCode.Exclude
	private Set<Rating> ratings;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "saloon")
	@EqualsAndHashCode.Exclude
	private Set<Favourite> favourites;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
	@EqualsAndHashCode.Exclude
	private Set<Reservation> reservations;
	
}















