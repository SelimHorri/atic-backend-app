package tn.cita.app.mapper;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Credential;
import tn.cita.app.domain.entity.Employee;
import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.domain.entity.UserImage;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.request.RegisterRequest;
import tn.cita.app.util.RegistrationUtils;

public interface EmployeeMapper {
	
	public static EmployeeDto map(@NotNull final Employee employee) {
		
		final var userImage = Optional
				.ofNullable(employee.getUserImage())
				.orElseGet(UserImage::new);
		final var manager = Optional
				.ofNullable(employee.getManager())
				.orElseGet(Employee::new);
		final var saloon = Optional
				.ofNullable(employee.getSaloon())
				.orElseGet(Saloon::new);
		
		return EmployeeDto.builder()
				.id(employee.getId())
				.firstname(employee.getFirstname())
				.lastname(employee.getLastname())
				.email(employee.getEmail())
				.phone(employee.getPhone())
				.birthdate(employee.getBirthdate())
				.userImageId(userImage.getId())
				.managerId(manager.getId())
				.credentialId(employee.getCredential().getId())
				.saloonId(saloon.getId())
				.build();
	}
	
	public static Employee map(@NotNull final EmployeeDto employeeDto) {
		return Employee.builder()
				.id(employeeDto.getId())
				.firstname(employeeDto.getFirstname())
				.lastname(employeeDto.getLastname())
				.email(employeeDto.getEmail())
				.phone(employeeDto.getPhone())
				.birthdate(employeeDto.getBirthdate())
				.userImage(
					UserImage.builder()
						.id(employeeDto.getUserImageId())
						.build())
				.manager(
					Employee.builder()
						.id(employeeDto.getManagerId())
						.build())
				.credential(
					Credential.builder()
						.id(employeeDto.getCredentialId())
						.build())
				.saloon(
					Saloon.builder()
						.id(employeeDto.getSaloonId())
						.build())
				.build();
	}
	
	public static Employee map(final RegisterRequest registerRequest) {
		return Employee.builder()
				.firstname(registerRequest.getFirstname())
				.lastname(registerRequest.getLastname())
				.email(registerRequest.getEmail())
				.phone(registerRequest.getPhone())
				.birthdate(registerRequest.getBirthdate())
				.userImage(null)
				.credential(
						Credential.builder()
						.username(registerRequest.getUsername())
						.password(registerRequest.getPassword())
						.userRoleBasedAuthority(RegistrationUtils.checkUserRoleBasedAuthority(registerRequest.getRole()))
						.isEnabled(false)
						.isAccountNonExpired(true)
						.isAccountNonLocked(true)
						.isCredentialsNonExpired(true)
						.build())
				.build();
	}
	
	
	
}













