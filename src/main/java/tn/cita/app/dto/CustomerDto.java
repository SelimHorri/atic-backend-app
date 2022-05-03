package tn.cita.app.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.constant.AppConstant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public final class CustomerDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Input firstname should not be blank")
	private String firstname;
	
	@NotBlank(message = "Input firstname should not be blank")
	private String lastname;
	
	@Email(message = "Input email should be in email format")
	@NotBlank(message = "Input email should not be blank")
	private String email;
	
	@Size(message = "Input phone should be in a phone number format", min = 8, max = 12)
	private String phone;
	
	@JsonFormat(pattern = AppConstant.LOCAL_DATE_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstant.LOCAL_DATE_FORMAT)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate birthdate;
	
	private String facebookUrl;
	private String instagramUrl;
	private String linkedinUrl;
	
	@JsonIgnore
	@JsonProperty("userImage")
	private UserImageDto userImageDto;
	
	@NotNull(message = "Input credential should not be null")
	@JsonProperty("credential")
	private CredentialDto credentialDto;
	
	@JsonIgnore
	private Set<RatingDto> ratingDtos;
	
	@JsonIgnore
	private Set<FavouriteDto> favouriteDtos;
	
	@JsonIgnore
	private Set<ReservationDto> reservationDtos;
	
}















