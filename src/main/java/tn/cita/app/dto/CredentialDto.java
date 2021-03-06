package tn.cita.app.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.domain.UserRoleBasedAuthority;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public final class CredentialDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Input username should be unique & not blank")
	private String username;
	
	@NotEmpty(message = "Input password should not be empty")
	private String password;
	
	@JsonProperty("role")
	@NotNull(message = "Input role should not be null")
	private UserRoleBasedAuthority userRoleBasedAuthority;
	private Boolean isEnabled;
	private Boolean isAccountNonExpired;
	private Boolean isAccountNonLocked;
	private Boolean isCredentialsNonExpired;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("customer")
	private CustomerDto customerDto;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("employee")
	private EmployeeDto employeeDto;
	
	@JsonIgnore
	private Set<VerificationTokenDto> verificationTokenDtos;
	
}











