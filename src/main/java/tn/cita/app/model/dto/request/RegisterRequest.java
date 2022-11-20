package tn.cita.app.model.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public record RegisterRequest(
		
		@NotBlank(message = "Input firstname should not be blank")
		String firstname,
		
		@NotBlank(message = "Input lastname should not be blank")
		String lastname,
		
		@Email(message = "Input email should be in email format")
		@NotBlank(message = "Input email should not be blank")
		String email,
		
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







