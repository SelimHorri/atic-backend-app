package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Customer;
import tn.cita.app.domain.entity.Employee;
import tn.cita.app.domain.entity.Rating;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.RatingDto;

public interface RatingMapper {
	
	public static RatingDto map(@NotNull final Rating rating) {
		return RatingDto.builder()
				.identifier(rating.getIdentifier())
				.workerId(rating.getWorkerId())
				.customerId(rating.getCustomerId())
				.rateDate(rating.getRateDate())
				.rate(rating.getRate())
				.description(rating.getDescription())
				.workerDto(
					EmployeeDto.builder()
						.id(rating.getWorker().getId())
						.identifier(rating.getWorker().getIdentifier())
						.ssn(rating.getWorker().getSsn())
						.firstname(rating.getWorker().getFirstname())
						.lastname(rating.getWorker().getLastname())
						.isMale(rating.getWorker().getIsMale())
						.email(rating.getWorker().getEmail())
						.phone(rating.getWorker().getPhone())
						.birthdate(rating.getWorker().getBirthdate())
						.build())
				.customerDto(
					CustomerDto.builder()
						.id(rating.getCustomer().getId())
						.identifier(rating.getCustomer().getIdentifier())
						.ssn(rating.getCustomer().getSsn())
						.firstname(rating.getCustomer().getFirstname())
						.lastname(rating.getCustomer().getLastname())
						.isMale(rating.getCustomer().getIsMale())
						.email(rating.getCustomer().getEmail())
						.phone(rating.getCustomer().getPhone())
						.birthdate(rating.getCustomer().getBirthdate())
						.build())
				.build();
	}
	
	public static Rating map(@NotNull final RatingDto ratingDto) {
		return Rating.builder()
				.identifier(ratingDto.getIdentifier())
				.workerId(ratingDto.getWorkerId())
				.customerId(ratingDto.getCustomerId())
				.rateDate(ratingDto.getRateDate())
				.rate(ratingDto.getRate())
				.description(ratingDto.getDescription())
				.worker(
					Employee.builder()
						.id(ratingDto.getWorkerDto().getId())
						.identifier(ratingDto.getWorkerDto().getIdentifier())
						.ssn(ratingDto.getWorkerDto().getSsn())
						.firstname(ratingDto.getWorkerDto().getFirstname())
						.lastname(ratingDto.getWorkerDto().getLastname())
						.isMale(ratingDto.getWorkerDto().getIsMale())
						.email(ratingDto.getWorkerDto().getEmail())
						.phone(ratingDto.getWorkerDto().getPhone())
						.birthdate(ratingDto.getWorkerDto().getBirthdate())
						.build())
				.customer(
					Customer.builder()
						.id(ratingDto.getCustomerDto().getId())
						.identifier(ratingDto.getCustomerDto().getIdentifier())
						.ssn(ratingDto.getCustomerDto().getSsn())
						.firstname(ratingDto.getCustomerDto().getFirstname())
						.lastname(ratingDto.getCustomerDto().getLastname())
						.isMale(ratingDto.getCustomerDto().getIsMale())
						.email(ratingDto.getCustomerDto().getEmail())
						.phone(ratingDto.getCustomerDto().getPhone())
						.birthdate(ratingDto.getCustomerDto().getBirthdate())
						.build())
				.build();
	}
	
	
	
}














