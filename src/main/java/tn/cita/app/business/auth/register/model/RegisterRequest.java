package tn.cita.app.business.auth.register.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import tn.cita.app.constant.AppConstants;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record RegisterRequest(
		
		@NotBlank(message = "Input firstname should not be blank")
		String firstname,
		
		@NotBlank(message = "Input lastname should not be blank")
		String lastname,
		
		@NotBlank(message = "Input email should not be blank")
		@Email(message = "Input email should be in email format")
		String email,
		
		@NotBlank(message = "Input phone should not be blank")
		@Size(message = "Input phone should be in a phone number format", min = 8, max = 12)
		String phone,
		
		@JsonFormat(pattern = AppConstants.LOCAL_DATE_FORMAT, shape = Shape.STRING)
		@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_FORMAT)
		@JsonSerialize(using = LocalDateSerializer.class)
		@JsonDeserialize(using = LocalDateDeserializer.class)
		LocalDate birthdate,
		
		@NotBlank(message = "Input username should not be blank")
		String username,
		
		@NotNull(message = "Input password should not be null")
		String password,
		
		@NotNull(message = "Input confirmPassword should not be null")
		String confirmPassword,
		
		@NotBlank(message = "Input role should not be blank")
		String role) implements Serializable {}




