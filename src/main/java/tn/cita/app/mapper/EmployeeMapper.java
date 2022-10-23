package tn.cita.app.mapper;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Credential;
import tn.cita.app.domain.entity.Employee;
import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.domain.entity.UserImage;
import tn.cita.app.dto.CredentialDto;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.SaloonDto;
import tn.cita.app.dto.UserImageDto;
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
				.identifier(employee.getIdentifier())
				.ssn(employee.getSsn())
				.firstname(employee.getFirstname())
				.lastname(employee.getLastname())
				.isMale(employee.getIsMale())
				.email(employee.getEmail())
				.phone(employee.getPhone())
				.birthdate(employee.getBirthdate())
				.hiredate(employee.getHiredate())
				.userImageDto(
					UserImageDto.builder()
						.id(userImage.getId())
						.identifier(userImage.getIdentifier())
						.imageLob(userImage.getImageLob())
						.build())
				.managerDto(
					EmployeeDto.builder()
						.id(manager.getId())
						.identifier(manager.getIdentifier())
						.firstname(manager.getFirstname())
						.lastname(manager.getLastname())
						.isMale(manager.getIsMale())
						.email(manager.getEmail())
						.phone(manager.getPhone())
						.birthdate(manager.getBirthdate())
						.hiredate(manager.getHiredate())
						.build())
				.credentialDto(
					CredentialDto.builder()
						.id(employee.getCredential().getId())
						.identifier(employee.getCredential().getIdentifier())
						.username(employee.getCredential().getUsername())
						.password(employee.getCredential().getPassword())
						.userRoleBasedAuthority(employee.getCredential().getUserRoleBasedAuthority())
						.isEnabled(employee.getCredential().getIsEnabled())
						.isAccountNonExpired(employee.getCredential().getIsAccountNonExpired())
						.isAccountNonLocked(employee.getCredential().getIsAccountNonLocked())
						.isCredentialsNonExpired(employee.getCredential().getIsCredentialsNonExpired())
						.build())
				.saloonDto(
					SaloonDto.builder()
						.id(saloon.getId())
						.identifier(saloon.getIdentifier())
						.code(saloon.getCode())
						.taxRef(saloon.getTaxRef())
						.name(saloon.getName())
						.isPrimary(saloon.getIsPrimary())
						.openingDate(saloon.getOpeningDate())
						.fullAdr(saloon.getFullAdr())
						.email(saloon.getEmail())
						.build())
				.build();
	}
	
	public static Employee map(@NotNull final EmployeeDto employeeDto) {
		
		final var userImageDto = Optional
				.ofNullable(employeeDto.getUserImageDto())
				.orElseGet(UserImageDto::new);
		final var managerDto = Optional
				.ofNullable(employeeDto.getManagerDto())
				.orElseGet(EmployeeDto::new);
		final var saloonDto = Optional
				.ofNullable(employeeDto.getSaloonDto())
				.orElseGet(SaloonDto::new);
		
		return Employee.builder()
				.id(employeeDto.getId())
				.identifier(employeeDto.getIdentifier())
				.ssn(employeeDto.getSsn())
				.firstname(employeeDto.getFirstname())
				.lastname(employeeDto.getLastname())
				.isMale(employeeDto.getIsMale())
				.email(employeeDto.getEmail())
				.phone(employeeDto.getPhone())
				.birthdate(employeeDto.getBirthdate())
				.hiredate(employeeDto.getHiredate())
				.userImage(
					UserImage.builder()
						.id(userImageDto.getId())
						.identifier(userImageDto.getIdentifier())
						.imageLob(userImageDto.getImageLob())
						.build())
				.manager(
					Employee.builder()
						.id(managerDto.getId())
						.identifier(managerDto.getIdentifier())
						.firstname(managerDto.getFirstname())
						.lastname(managerDto.getLastname())
						.isMale(managerDto.getIsMale())
						.email(managerDto.getEmail())
						.phone(managerDto.getPhone())
						.birthdate(managerDto.getBirthdate())
						.hiredate(managerDto.getHiredate())
						.build())
				.credential(
					Credential.builder()
						.id(employeeDto.getCredentialDto().getId())
						.identifier(employeeDto.getCredentialDto().getIdentifier())
						.username(employeeDto.getCredentialDto().getUsername())
						.password(employeeDto.getCredentialDto().getPassword())
						.userRoleBasedAuthority(employeeDto.getCredentialDto().getUserRoleBasedAuthority())
						.isEnabled(employeeDto.getCredentialDto().getIsEnabled())
						.isAccountNonExpired(employeeDto.getCredentialDto().getIsAccountNonExpired())
						.isAccountNonLocked(employeeDto.getCredentialDto().getIsAccountNonLocked())
						.isCredentialsNonExpired(employeeDto.getCredentialDto().getIsCredentialsNonExpired())
						.build())
				.saloon(
					Saloon.builder()
						.id(saloonDto.getId())
						.identifier(saloonDto.getIdentifier())
						.code(saloonDto.getCode())
						.taxRef(saloonDto.getTaxRef())
						.name(saloonDto.getName())
						.isPrimary(saloonDto.getIsPrimary())
						.openingDate(saloonDto.getOpeningDate())
						.fullAdr(saloonDto.getFullAdr())
						.email(saloonDto.getEmail())
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













