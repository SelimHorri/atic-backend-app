package tn.cita.app.business.profile.employee.worker.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Builder;
import tn.cita.app.constant.AppConstants;

@Builder
public record WorkerProfileRequest(
		
		@NotBlank(message = "Must specify authenticated username")
		String authenticatedUsername,
		
		@NotBlank(message = "Firstname must not be blank")
		String firstname,
		
		@NotBlank(message = "Lastname must not be blank")
		String lastname,
		
		@Email(message = "Must follow email format")
		@NotBlank(message = "Email must not be blank")
		String email,
		
		@Size(min = 8, max = 12, message = "Phone must be between {min} and {max} characters")
		@NotBlank(message = "Phone must not be blank")
		String phone,
		
		@JsonFormat(pattern = AppConstants.LOCAL_DATE_FORMAT, shape = Shape.STRING)
		@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_FORMAT)
		@JsonSerialize(using = LocalDateSerializer.class)
		@JsonDeserialize(using = LocalDateDeserializer.class)
		LocalDate birthdate,
		
		@JsonFormat(pattern = AppConstants.LOCAL_DATE_FORMAT, shape = Shape.STRING)
		@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_FORMAT)
		@JsonSerialize(using = LocalDateSerializer.class)
		@JsonDeserialize(using = LocalDateDeserializer.class)
		LocalDate hiredate,
		
		@NotBlank(message = "Username must not be blank")
		String username,
		
		@NotBlank(message = "Password must not be blank")
		String password,
		
		@NotBlank(message = "confirmPassword must not be blank")
		String confirmPassword) implements Serializable {}



