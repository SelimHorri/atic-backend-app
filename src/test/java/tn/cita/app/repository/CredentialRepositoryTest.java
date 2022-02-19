package tn.cita.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import tn.cita.app.container.AbstractTestSharedMySQLContainer;
import tn.cita.app.domain.entity.Credential;

@DataJpaTest(showSql = true)
@AutoConfigureDataJpa
class CredentialRepositoryTest extends AbstractTestSharedMySQLContainer {
	
	@Autowired
	private CredentialRepository credentialRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@Test
	void givenValidUsername_whenFindCredentialByUsernameIgnoringCase_thenCredentialShouldBeFound() {
		
		final var username = "selimhorri";
		final var optionalCredential = this.credentialRepository.findByUsernameIgnoreCase(username.toUpperCase());
		
		assertThat(optionalCredential).isNotNull();
		assertThat(optionalCredential.isPresent()).isTrue();
		assertThat(optionalCredential.get()).isNotNull();
		assertThat(optionalCredential.get()).isInstanceOf(Credential.class);
		assertThat(optionalCredential.get().getUsername()).isEqualTo(username);
		assertThat(optionalCredential.get().getCustomer()).isNull();
	}
	
	
	
}












