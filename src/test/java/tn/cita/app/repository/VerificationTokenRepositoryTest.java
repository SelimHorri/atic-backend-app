package tn.cita.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import tn.cita.app.container.AbstractTestSharedMySQLContainer;
import tn.cita.app.domain.entity.VerificationToken;

@DataJpaTest(showSql = true)
@AutoConfigureDataJpa
class VerificationTokenRepositoryTest extends AbstractTestSharedMySQLContainer {
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Test
	void givenValidToken_whenFindByToken_thenOptionalOfVerificationTokenShouldBeFound() {
		
		final var token = "c856b457-ed66-4dd4-bc1a-f0be552a28e5";
		final var optionalVerificationToken = this.verificationTokenRepository.findByToken(token);
		
		assertThat(optionalVerificationToken)
				.isNotNull()
				.isPresent();
		assertThat(optionalVerificationToken.get())
				.isNotNull()
				.isInstanceOf(VerificationToken.class);
		assertThat(optionalVerificationToken.get().getToken()).isEqualTo(token);
		assertThat(optionalVerificationToken.get().getCredential()).isNotNull();
		assertThat(optionalVerificationToken.get().getCredential().getUsername()).isEqualTo("jamesbond");
	}
	
	
	
}
















