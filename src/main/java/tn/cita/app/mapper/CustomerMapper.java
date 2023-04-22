package tn.cita.app.mapper;

import java.util.Objects;

import lombok.NonNull;
import tn.cita.app.business.auth.register.model.RegisterRequest;
import tn.cita.app.model.domain.entity.Credential;
import tn.cita.app.model.domain.entity.Customer;
import tn.cita.app.model.domain.entity.UserImage;
import tn.cita.app.model.dto.CredentialDto;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.UserImageDto;
import tn.cita.app.util.UserRoleUtils;

public interface CustomerMapper {
	
	public static CustomerDto toDto(@NonNull final Customer customer) {
		
		final var userImage = Objects
				.requireNonNullElseGet(customer.getUserImage(), UserImage::new);
		
		return CustomerDto.builder()
				.id(customer.getId())
				.identifier(customer.getIdentifier())
				.ssn(customer.getSsn())
				.firstname(customer.getFirstname())
				.lastname(customer.getLastname())
				.isMale(customer.getIsMale())
				.email(customer.getEmail())
				.phone(customer.getPhone())
				.birthdate(customer.getBirthdate())
				.facebookUrl(customer.getFacebookUrl())
				.instagramUrl(customer.getInstagramUrl())
				.linkedinUrl(customer.getLinkedinUrl())
				.userImageDto(
					UserImageDto.builder()
						.id(userImage.getId())
						.identifier(userImage.getIdentifier())
						.imageLob(userImage.getImageLob())
						.build())
				.credentialDto(
					CredentialDto.builder()
						.id(customer.getCredential().getId())
						.identifier(customer.getCredential().getIdentifier())
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
	
	public static Customer map(@NonNull final RegisterRequest registerRequest) {
		return Customer.builder()
				.firstname(registerRequest.firstname())
				.lastname(registerRequest.lastname())
				.email(registerRequest.email())
				.phone(registerRequest.phone())
				.birthdate(registerRequest.birthdate())
				.credential(
						Credential.builder()
						.username(registerRequest.username())
						.password(registerRequest.password())
						.userRoleBasedAuthority(UserRoleUtils.checkUserRoleBasedAuthority(registerRequest.role()))
						.isEnabled(false)
						.isAccountNonExpired(true)
						.isAccountNonLocked(true)
						.isCredentialsNonExpired(true)
						.build())
				.build();
	}
	
}







