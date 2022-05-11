package tn.cita.app.mapper;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Credential;
import tn.cita.app.domain.entity.Customer;
import tn.cita.app.domain.entity.UserImage;
import tn.cita.app.dto.CustomerDto;
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
				.facebookUrl(customer.getFacebookUrl())
				.instagramUrl(customer.getInstagramUrl())
				.linkedinUrl(customer.getLinkedinUrl())
				.userImageId(userImage.getId())
				.credentialId(customer.getCredential().getId())
				.build();
	}
	
	public static Customer map(@NotNull final CustomerDto customerDto) {
		return Customer.builder()
				.id(customerDto.getId())
				.firstname(customerDto.getFirstname())
				.lastname(customerDto.getLastname())
				.email(customerDto.getEmail())
				.phone(customerDto.getPhone())
				.birthdate(customerDto.getBirthdate())
				.facebookUrl(customerDto.getFacebookUrl())
				.instagramUrl(customerDto.getInstagramUrl())
				.linkedinUrl(customerDto.getLinkedinUrl())
				.userImage(
					UserImage.builder()
						.id(customerDto.getUserImageId())
						.build())
				.credential(
					Credential.builder()
						.id(customerDto.getCredentialId())
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














