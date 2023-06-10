package tn.cita.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.model.domain.UserRoleBasedAuthority;
import tn.cita.app.model.domain.entity.Credential;
import tn.cita.app.model.domain.entity.Customer;
import tn.cita.app.model.domain.entity.UserImage;
import tn.cita.app.model.dto.CredentialDto;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.UserImageDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.service.CustomerService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CustomerServiceImplTest {
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	private Customer customer;
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.customer = Customer.builder()
				.id(1)
				.firstname("selim")
				.lastname("horri")
				.email("@gmail.com")
				.phone("22125144")
				.birthdate(LocalDate.of(1995, 1, 9))
				.userImage(new UserImage())
				.credential(
						Credential.builder()
							.username("selimhorri")
							.userRoleBasedAuthority(UserRoleBasedAuthority.CUSTOMER)
							.isEnabled(true)
							.build())
				.build();
	}
	
	@Disabled
	@DisplayName("test findAll(offset) method")
	@Test
	void givenPageOffset_whenFindAllBasedOnGivenPageOffset_thenAllCustomerDtoListBasedOnPageOffsetShouldBeFound() {
		
		final var mockFindAllCustomers = List.of(Customer.builder()
				.id(1)
				.firstname("selim")
				.lastname("horri")
				.email("@gmail.com")
				.phone("22125144")
				.birthdate(LocalDate.of(1995, 1, 9))
				.userImage(new UserImage())
				.credential(
						Credential.builder()
							.username("selimhorri")
							.userRoleBasedAuthority(UserRoleBasedAuthority.CUSTOMER)
							.isEnabled(true)
							.build())
				.build(), 
				Customer.builder()
				.id(2)
				.firstname("amine")
				.lastname("ladjimi")
				.email("@gmail.com")
				.phone("22125144")
				.birthdate(LocalDate.of(1995, 1, 9))
				.userImage(new UserImage())
				.credential(
						Credential.builder()
							.username("amineladjimi")
							.userRoleBasedAuthority(UserRoleBasedAuthority.CUSTOMER)
							.isEnabled(true)
							.build())
				.build());
		
		final var expectedFindAllCustomerDtos = List.of(CustomerDto.builder()
				.id(1)
				.firstname("selim")
				.lastname("horri")
				.email("@gmail.com")
				.phone("22125144")
				.birthdate(LocalDate.of(1995, 1, 9))
				.userImageDto(new UserImageDto())
				.credentialDto(
						CredentialDto.builder()
							.username("selimhorri")
							.userRoleBasedAuthority(UserRoleBasedAuthority.CUSTOMER)
							.isEnabled(true)
							.build())
				.build(), 
				CustomerDto.builder()
				.id(2)
				.firstname("amine")
				.lastname("ladjimi")
				.email("@gmail.com")
				.phone("22125144")
				.birthdate(LocalDate.of(1995, 1, 9))
				.userImageDto(new UserImageDto())
				.credentialDto(
					CredentialDto.builder()
						.username("amineladjimi")
						.userRoleBasedAuthority(UserRoleBasedAuthority.CUSTOMER)
						.isEnabled(true)
						.build())
				.build());
		
		final var clientPageRequest = new ClientPageRequest(0, 0, null, null);
		when(this.customerRepository.findAll(PageRequest.of(clientPageRequest.getOffset() - 1, clientPageRequest.getSize())))
				.thenReturn(new PageImpl<>(mockFindAllCustomers));
		
		final var findAll = this.customerService.findAll(clientPageRequest);
		
		assertThat(findAll)
				.isNotNull()
				.isNotEmpty()
				.hasSameSizeAs(expectedFindAllCustomerDtos)
				.allSatisfy(c -> {
					assertThat(c.getId()).isNotNull();
					assertThat(c.getEmail()).isEqualTo("@gmail.com");
					assertThat(c.getPhone()).isEqualTo("22125144");
					assertThat(c.getCredentialDto()).isNotNull();
					assertThat(c.getCredentialDto().getUserRoleBasedAuthority().name()).isEqualTo(UserRoleBasedAuthority.CUSTOMER.name());
					assertThat(c.getCredentialDto().getIsEnabled()).isTrue();
				});
		
	}
	
	@Test
	void givenCustomerId_whenFindById_thenCustomerDtoShouldBeFound() {
		
		final int id = 1;
		
		when(this.customerRepository.findById(id))
				.thenReturn(Optional.ofNullable(this.customer));
		
		final var expectedCustomerDto = CustomerDto.builder()
				.id(1)
				.firstname("selim")
				.lastname("horri")
				.email("@gmail.com")
				.phone("22125144")
				.birthdate(LocalDate.of(1995, 1, 9))
				.userImageDto(new UserImageDto())
				.credentialDto(
					CredentialDto.builder()
						.username("selimhorri")
						.userRoleBasedAuthority(UserRoleBasedAuthority.CUSTOMER)
						.isEnabled(true)
						.build())
				.build();
		
		final var customerDto = this.customerService.findById(id);
		
		assertThat(customerDto).isNotNull();
		assertThat(customerDto.getId()).isNotNull();
		assertThat(customerDto.getFirstname()).isEqualTo(expectedCustomerDto.getFirstname());
		assertThat(customerDto.getLastname()).isEqualTo(expectedCustomerDto.getLastname());
		assertThat(customerDto.getEmail()).isEqualTo(expectedCustomerDto.getEmail());
		assertThat(customerDto.getPhone()).isEqualTo(expectedCustomerDto.getPhone());
		assertThat(customerDto.getBirthdate()).isEqualTo(expectedCustomerDto.getBirthdate());
		assertThat(customerDto.getUserImageDto()).isNotNull();
		assertThat(customerDto.getCredentialDto()).isNotNull();
		assertThat(customerDto.getCredentialDto().getUsername())
				.isEqualTo(expectedCustomerDto.getCredentialDto().getUsername());
		assertThat(customerDto.getCredentialDto().getUserRoleBasedAuthority())
				.isEqualTo(expectedCustomerDto.getCredentialDto().getUserRoleBasedAuthority());
		assertThat(customerDto.getCredentialDto().getIsEnabled())
				.isEqualTo(expectedCustomerDto.getCredentialDto().getIsEnabled());
	}
	
	@Test
	void givenInvalidCustomerId_whenFindById_thenCustomerNotFoundExceptionShouldBeThrown() {
		
		final int wrongId = -100;
		final var customerNotFoundException = assertThrows(CustomerNotFoundException.class, () -> this.customerService.findById(wrongId));
		
		assertThat(customerNotFoundException).isNotNull();
		assertThat(customerNotFoundException.getMessage())
				.isNotBlank()
				.startsWith("Customer ")
				.endsWith("not found")
				.isEqualTo(String.format("Customer not found", wrongId));
	}
	
	@Test
	void givenValidAndInvalidCustomerId_whenDeleteById_thenCustomerShouldBeDeleted() {
		
		int id = 1;
		when(this.customerRepository.existsById(id))
				.thenReturn(true);
		
		boolean deleteById = this.customerService.deleteById(id);
		assertThat(deleteById).isFalse();
		
		id = -100;
		when(this.customerRepository.existsById(id))
				.thenReturn(false);
		
		deleteById = this.customerService.deleteById(id);
		assertThat(deleteById).isTrue();
	}
	
}





