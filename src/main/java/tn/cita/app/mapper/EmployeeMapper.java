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

public interface EmployeeMapper {
	
	public static EmployeeDto map(@NotNull final Employee employee) {
		
		final var userImage = Optional
				.ofNullable(employee.getUserImage())
				.orElseGet(UserImage::new);
		final var manager = Optional
				.ofNullable(employee.getManager())
				.orElseGet(Employee::new);
		
		return EmployeeDto.builder()
				.id(employee.getId())
				.firstname(employee.getFirstname())
				.lastname(employee.getLastname())
				.email(employee.getEmail())
				.phone(employee.getPhone())
				.birthdate(employee.getBirthdate())
				.userImageDto(
					UserImageDto.builder()
						.id(userImage.getId())
						.imageLob(userImage.getImageLob())
						.build())
				.managerDto(
					EmployeeDto.builder()
						.id(manager.getId())
						.firstname(manager.getFirstname())
						.lastname(manager.getLastname())
						.email(manager.getEmail())
						.phone(manager.getPhone())
						.birthdate(manager.getBirthdate())
						.build())
				.credentialDto(
					CredentialDto.builder()
						.id(employee.getCredential().getId())
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
						.id(employee.getSaloon().getId())
						.code(employee.getSaloon().getCode())
						.name(employee.getSaloon().getName())
						.isPrimary(employee.getSaloon().getIsPrimary())
						.openingDate(employee.getSaloon().getOpeningDate())
						.fullAdr(employee.getSaloon().getFullAdr())
						.email(employee.getSaloon().getEmail())
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
		
		return Employee.builder()
				.id(employeeDto.getId())
				.firstname(employeeDto.getFirstname())
				.lastname(employeeDto.getLastname())
				.email(employeeDto.getEmail())
				.phone(employeeDto.getPhone())
				.birthdate(employeeDto.getBirthdate())
				.userImage(
					UserImage.builder()
						.id(userImageDto.getId())
						.imageLob(userImageDto.getImageLob())
						.build())
				.manager(
					Employee.builder()
						.id(managerDto.getId())
						.firstname(managerDto.getFirstname())
						.lastname(managerDto.getLastname())
						.email(managerDto.getEmail())
						.phone(managerDto.getPhone())
						.birthdate(managerDto.getBirthdate())
						.build())
				.credential(
					Credential.builder()
						.id(employeeDto.getCredentialDto().getId())
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
						.id(employeeDto.getSaloonDto().getId())
						.code(employeeDto.getSaloonDto().getCode())
						.name(employeeDto.getSaloonDto().getName())
						.isPrimary(employeeDto.getSaloonDto().getIsPrimary())
						.openingDate(employeeDto.getSaloonDto().getOpeningDate())
						.fullAdr(employeeDto.getSaloonDto().getFullAdr())
						.email(employeeDto.getSaloonDto().getEmail())
						.build())
				.build();
	}
	
	
	
}













