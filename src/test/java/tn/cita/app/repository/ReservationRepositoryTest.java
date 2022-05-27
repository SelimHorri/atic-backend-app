package tn.cita.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import tn.cita.app.constant.AppConstant;
import tn.cita.app.container.AbstractSharedMySQLTestContainer;
import tn.cita.app.domain.ReservationStatus;
import tn.cita.app.domain.entity.Customer;
import tn.cita.app.domain.entity.Reservation;

@DataJpaTest(showSql = true)
class ReservationRepositoryTest extends AbstractSharedMySQLTestContainer {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Test
	void givenValidCustomerId_whenFindAllByCustomerId_thenCustomerShouldBeFound() {
		
		final int customerId = 5;
		
		/**
		 * 86d1523e-2788-4ef0-8849-fe26c396360b
		 * 566996bf-179f-4db5-960e-e0351babd194
		 * e65e223e-c7cb-4590-b9a4-5fe9ed934c93
		 * 4126efd8-c456-45ae-b4ce-be589d2ffde6
		 * 90c9b98b-347e-4380-a517-f504a70124d0
		*/
		
		final var expectedReservations = List.of(
				Reservation.builder()
				.id(null)
				.code(null)
				.build(),
				Reservation.builder()
				.id(null)
				
				.build(),
				Reservation.builder()
				.id(null)
				
				.build(),
				Reservation.builder()
				.id(null)
				
				.build(),
				Reservation.builder()
				.id(null)
				
				.build()
				);
		
		final var reservations = this.reservationRepository.findAllByCustomerId(customerId, PageRequest.of(1 - 1, AppConstant.PAGE_SIZE));
		
		assertThat(reservations)
				.isNotNull()
				.hasSameSizeAs(expectedReservations);
	}
	
	@Test
	void givenValidCode_whenFindByCode_thenOptionalOfReservationShouldBeFound() {
		
		final var code = "86d1523e-2788-4ef0-8849-fe26c396360b";
		
		final var expectedReservation = Reservation.builder()
				.id(null)
				.code(code)
				.description("ASAP")
				.startDate(LocalDateTime.now())
				.status(ReservationStatus.NOT_STARTED)
				.customer(Customer.builder()
						.id(null)
						.firstname("cristiano")
						.lastname("ronaldo")
						.birthdate(null)
						.build())
				.build();
		
		final var optionalReservation = this.reservationRepository.findByCode(code);
		
		assertThat(optionalReservation)
				.isNotNull()
				.containsInstanceOf(Reservation.class)
				.hasValueSatisfying(r -> {
					assertThat(r.getCode()).isEqualTo(expectedReservation.getCode());
					assertThat(r.getDescription()).isEqualTo(expectedReservation.getDescription());
					assertThat(r.getStartDate().getHour()).isEqualTo(expectedReservation.getStartDate().getHour());
					assertThat(r.getStatus()).isEqualTo(expectedReservation.getStatus());
					assertThat(r.getCustomer()).isNotNull();
					assertThat(r.getCustomer().getFirstname()).isEqualTo(expectedReservation.getCustomer().getFirstname());
					assertThat(r.getCustomer().getLastname()).isEqualTo(expectedReservation.getCustomer().getLastname());
					assertThat(r.getCustomer().getBirthdate()).isEqualTo(expectedReservation.getCustomer().getBirthdate());
				});
	}
	
	
	
}











