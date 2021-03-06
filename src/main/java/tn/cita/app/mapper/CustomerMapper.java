package tn.cita.app.mapper;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Credential;
import tn.cita.app.domain.entity.Customer;
import tn.cita.app.domain.entity.UserImage;
import tn.cita.app.dto.CredentialDto;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.UserImageDto;
import tn.cita.app.dto.request.RegisterRequest;
import tn.cita.app.util.RegistrationUtils;

public interface CustomerMapper {
	
	public static CustomerDto map(@NotNull final Customer customer) {
		
		final var userImage = Optional
				.ofNullable(customer.getUserImage())
				.orElseGet(UserImage::new);
		
		return CustomerDto.builder()
				.id(customer.getId())
				.firstname(customer.getFirstname())
				.lastname(customer.getLastname())
				.email(customer.getEmail())
				.phone(customer.getPhone())
				.birthdate(customer.getBirthdate())
				.userImageDto(
					UserImageDto.builder()
						.id(userImage.getId())
						.imageLob(userImage.getImageLob())
						.build())
				.credentialDto(
					CredentialDto.builder()
						.id(customer.getId())
						.username(customer.getCredential().getUsername())
						.password(customer.getCredential().getPassword())
						.userRoleBasedAuthority(customer.getCredential().getUserRoleBasedAuthority())
						.isEnabled(customer.getCredential().getIsEnabled())
						.isAccountNonExpired(customer.getCredential().getIsAccountNonExpired())
						.isAccountNonLocked(customer.getCredential().getIsAccountNonLocked())
						.isCredentialsNonExpired(customer.getCredential().getIsCredentialsNonExpired())
						.build())
				.build();
	}
	
	public static Customer map(@NotNull final CustomerDto customerDto) {
		
		final var userImageDto = Optional
				.ofNullable(customerDto.getUserImageDto())
				.orElseGet(UserImageDto::new);
		
		return Customer.builder()
				.id(customerDto.getId())
				.firstname(customerDto.getFirstname())
				.lastname(customerDto.getLastname())
				.email(customerDto.getEmail())
				.phone(customerDto.getPhone())
				.birthdate(customerDto.getBirthdate())
				.userImage(
					UserImage.builder()
						.id(userImageDto.getId())
						.imageLob(userImageDto.getImageLob())
						.build())
				.credential(
					Credential.builder()
						.id(customerDto.getId())
						.username(customerDto.getCredentialDto().getUsername())
						.password(customerDto.getCredentialDto().getPassword())
						.userRoleBasedAuthority(customerDto.getCredentialDto().getUserRoleBasedAuthority())
						.isEnabled(customerDto.getCredentialDto().getIsEnabled())
						.isAccountNonExpired(customerDto.getCredentialDto().getIsAccountNonExpired())
						.isAccountNonLocked(customerDto.getCredentialDto().getIsAccountNonLocked())
						.isCredentialsNonExpired(customerDto.getCredentialDto().getIsCredentialsNonExpired())
						.build())
				.build();
	}
	
	public static Customer map(final RegisterRequest registerRequest) {
		return Customer.builder()
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














