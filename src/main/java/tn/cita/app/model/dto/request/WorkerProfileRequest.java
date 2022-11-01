package tn.cita.app.model.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.cita.app.constant.AppConstants;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class WorkerProfileRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Must specify authenticated username")
	private String authenticatedUsername;
	
	@NotBlank(message = "Firstname must not be blank")
	private String firstname;
	
	@NotBlank(message = "Lastname must not be blank")
	private String lastname;
	
	@Email(message = "Must follow email format")
	@NotBlank(message = "Email must not be blank")
	private String email;
	
	@Size(min = 8, max = 12, message = "Phone must be between {min} and {max} characters")
	@NotBlank(message = "Phone must not be blank")
	private String phone;
	
	@JsonFormat(pattern = AppConstants.LOCAL_DATE_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_FORMAT)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate birthdate;
	
	@JsonFormat(pattern = AppConstants.LOCAL_DATE_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_FORMAT)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate hiredate;
	
	@NotBlank(message = "Username must not be blank")
	private String username;
	
	@NotBlank(message = "Password must not be blank")
	private String password;
	
	@NotBlank(message = "confirmPassword must not be blank")
	private String confirmPassword;
	
}













