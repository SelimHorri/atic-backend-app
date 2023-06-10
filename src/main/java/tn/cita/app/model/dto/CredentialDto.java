package tn.cita.app.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.model.domain.UserRoleBasedAuthority;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public final class CredentialDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = -2844699799301772494L;
	
	@NotBlank(message = "Input username should be unique & not blank")
	private String username;
	
	@JsonIgnore
	@NotEmpty(message = "Input password should not be empty")
	private String password;
	
	@JsonProperty("role")
	@NotNull(message = "Input role should not be null")
	private UserRoleBasedAuthority userRoleBasedAuthority;
	private Boolean isEnabled;
	private Boolean isAccountNonExpired;
	private Boolean isAccountNonLocked;
	private Boolean isCredentialsNonExpired;
	
}



