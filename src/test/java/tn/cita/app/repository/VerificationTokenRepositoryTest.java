package tn.cita.app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.cita.app.container.AbstractSharedMySQLTestContainer;
import tn.cita.app.model.domain.entity.VerificationToken;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = true)
@AutoConfigureDataJpa
class VerificationTokenRepositoryTest extends AbstractSharedMySQLTestContainer {
	
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
	
	@Test
	void givenValidToken_whenDeleteByToken_thenOptionalOfThatTokenShouldBeEmpty() {
		final var token = "c856b457-ed66-4dd4-bc1a-f0be552a28e5";
		this.verificationTokenRepository.deleteByToken(token);
		final var optionalVerificationToken = this.verificationTokenRepository.findByToken(token);
		assertThat(optionalVerificationToken).isEmpty();
	}
	
}





