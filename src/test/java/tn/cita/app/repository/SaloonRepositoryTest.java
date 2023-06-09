package tn.cita.app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.container.AbstractSharedMySQLTestContainer;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = true)
@AutoConfigureDataJpa
class SaloonRepositoryTest extends AbstractSharedMySQLTestContainer {
	
	@Autowired
	private SaloonRepository saloonRepository;
	
	@Test
	void givenValidCode_whenFindAllByCustomerId_thenSaloonsShouldBeFound() {
		
		final var code = "290a9852-ee40-46d0-9979-c3493c0de833";
		
		final var saloons = this.saloonRepository.findAllByCode(code, PageRequest.of(1 - 1, AppConstants.PAGE_SIZE));
		
		assertThat(saloons)
				.isNotNull()
				.isNotEmpty()
				.allSatisfy(s -> {
					assertThat(s.getCode()).isEqualTo(code);
				});
		
	}
	
}






