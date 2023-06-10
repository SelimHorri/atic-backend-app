package tn.cita.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.model.domain.UserRoleBasedAuthority;
import tn.cita.app.model.domain.entity.Credential;
import tn.cita.app.model.domain.entity.Employee;
import tn.cita.app.model.domain.entity.UserImage;
import tn.cita.app.model.dto.CredentialDto;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.UserImageDto;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeServiceImplTest {
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	private Employee employee;
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.employee = Employee.builder()
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
							.userRoleBasedAuthority(UserRoleBasedAuthority.WORKER)
							.isEnabled(true)
							.build())
				.build();
	}
	
	@DisplayName("test findAll(offset) method")
	@Test
	void givenPageOffset_whenFindAllBasedOnGivenPageOffset_thenAllEmployeeDtoListBasedOnPageOffsetShouldBeFound() {
		
		final var mockFindAllEmployees = List.of(Employee.builder()
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
							.userRoleBasedAuthority(UserRoleBasedAuthority.WORKER)
							.isEnabled(true)
							.build())
				.build(), 
				Employee.builder()
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
							.userRoleBasedAuthority(UserRoleBasedAuthority.WORKER)
							.isEnabled(true)
							.build())
				.build());
		
		final var expectedFindAllEmployeeDtos = List.of(EmployeeDto.builder()
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
							.userRoleBasedAuthority(UserRoleBasedAuthority.WORKER)
							.isEnabled(true)
							.build())
				.build(), 
				EmployeeDto.builder()
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
						.userRoleBasedAuthority(UserRoleBasedAuthority.WORKER)
						.isEnabled(true)
						.build())
				.build());
		
		final int pageOffset = 1;
		when(this.employeeRepository.findAll(PageRequest.of(pageOffset - 1, AppConstants.PAGE_SIZE)))
				.thenReturn(new PageImpl<>(mockFindAllEmployees));
		
		final var findAll = this.employeeService.findAll(pageOffset);
		
		assertThat(findAll)
				.isNotNull()
				.isNotEmpty()
				.hasSameSizeAs(expectedFindAllEmployeeDtos)
				.allSatisfy(c -> {
					assertThat(c.getId()).isNotNull();
					assertThat(c.getEmail()).isEqualTo("@gmail.com");
					assertThat(c.getPhone()).isEqualTo("22125144");
					assertThat(c.getCredentialDto()).isNotNull();
					assertThat(c.getCredentialDto().getUserRoleBasedAuthority().name()).isEqualTo(UserRoleBasedAuthority.WORKER.name());
					assertThat(c.getCredentialDto().getIsEnabled()).isTrue();
				});
		
	}
	
	@Test
	void givenEmployeeId_whenFindById_thenEmployeeDtoShouldBeFound() {
		
		final int id = 1;
		
		when(this.employeeRepository.findById(id))
				.thenReturn(Optional.ofNullable(this.employee));
		
		final var expectedEmployeeDto = EmployeeDto.builder()
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
						.userRoleBasedAuthority(UserRoleBasedAuthority.WORKER)
						.isEnabled(true)
						.build())
				.build();
		
		final var employeeDto = this.employeeService.findById(id);
		
		assertThat(employeeDto).isNotNull();
		assertThat(employeeDto.getId()).isNotNull();
		assertThat(employeeDto.getFirstname()).isEqualTo(expectedEmployeeDto.getFirstname());
		assertThat(employeeDto.getLastname()).isEqualTo(expectedEmployeeDto.getLastname());
		assertThat(employeeDto.getEmail()).isEqualTo(expectedEmployeeDto.getEmail());
		assertThat(employeeDto.getPhone()).isEqualTo(expectedEmployeeDto.getPhone());
		assertThat(employeeDto.getBirthdate()).isEqualTo(expectedEmployeeDto.getBirthdate());
		assertThat(employeeDto.getUserImageDto()).isNotNull();
		assertThat(employeeDto.getCredentialDto()).isNotNull();
		assertThat(employeeDto.getCredentialDto().getUsername())
				.isEqualTo(expectedEmployeeDto.getCredentialDto().getUsername());
		assertThat(employeeDto.getCredentialDto().getUserRoleBasedAuthority())
				.isEqualTo(expectedEmployeeDto.getCredentialDto().getUserRoleBasedAuthority());
		assertThat(employeeDto.getCredentialDto().getIsEnabled())
				.isEqualTo(expectedEmployeeDto.getCredentialDto().getIsEnabled());
		
	}
	
	@Test
	void givenInvalidEmployeeId_whenFindById_thenEmployeeNotFoundExceptionShouldBeThrown() {
		
		final int wrongId = -100;
		final var employeeNotFoundException = assertThrows(EmployeeNotFoundException.class, () -> this.employeeService.findById(wrongId));
		
		assertThat(employeeNotFoundException).isNotNull();
		assertThat(employeeNotFoundException.getMessage())
				.isNotBlank()
				.startsWith("Employee ")
				.endsWith("not found")
				.isEqualTo(String.format("Employee not found", wrongId));
	}
	
	@Test
	void givenValidAndInvalidEmployeeId_whenDeleteById_thenEmployeeShouldBeDeleted() {
		
		int id = 1;
		when(this.employeeRepository.existsById(id))
				.thenReturn(true);
		
		boolean deleteById = this.employeeService.deleteById(id);
		assertThat(deleteById).isFalse();
		
		id = -100;
		when(this.employeeRepository.existsById(id))
				.thenReturn(false);
		
		deleteById = this.employeeService.deleteById(id);
		assertThat(deleteById).isTrue();
	}
	
}





