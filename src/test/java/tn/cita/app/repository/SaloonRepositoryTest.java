package tn.cita.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import tn.cita.app.container.AbstractSharedMySQLTestContainer;

@DataJpaTest(showSql = true)
class SaloonRepositoryTest extends AbstractSharedMySQLTestContainer {
	
	@Autowired
	private SaloonRepository saloonRepository;
	
	@Test
	void givenValidCode_whenFindAllByCustomerId_thenSaloonsShouldBeFound() {
		
		final var code = "290a9852-ee40-46d0-9979-c3493c0de833";
		
		final var saloons = this.saloonRepository.findAllByCode(code);
		
		assertThat(saloons)
				.isNotNull()
				.allSatisfy(s -> {
					assertThat(s.getCode()).isEqualTo(code);
				});
		
	}
	
	
	
}










