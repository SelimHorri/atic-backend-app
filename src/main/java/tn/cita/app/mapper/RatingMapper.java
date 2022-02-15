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
				.employeeId(rating.getEmployeeId())
				.customerId(rating.getCustomerId())
				.rateDate(rating.getRateDate())
				.rate(rating.getRate())
				.description(rating.getDescription())
				.employeeDto(
					EmployeeDto.builder()
						.id(rating.getEmployee().getId())
						.firstname(rating.getEmployee().getFirstname())
						.lastname(rating.getEmployee().getLastname())
						.email(rating.getEmployee().getEmail())
						.phone(rating.getEmployee().getPhone())
						.birthdate(rating.getEmployee().getBirthdate())
						.build())
				.customerDto(
					CustomerDto.builder()
						.id(rating.getCustomer().getId())
						.firstname(rating.getCustomer().getFirstname())
						.lastname(rating.getCustomer().getLastname())
						.email(rating.getCustomer().getEmail())
						.phone(rating.getCustomer().getPhone())
						.birthdate(rating.getCustomer().getBirthdate())
						.build())
				.build();
	}
	
	public static Rating map(@NotNull final RatingDto ratingDto) {
		return Rating.builder()
				.employeeId(ratingDto.getEmployeeId())
				.customerId(ratingDto.getCustomerId())
				.rateDate(ratingDto.getRateDate())
				.rate(ratingDto.getRate())
				.description(ratingDto.getDescription())
				.employee(
					Employee.builder()
						.id(ratingDto.getEmployeeDto().getId())
						.firstname(ratingDto.getEmployeeDto().getFirstname())
						.lastname(ratingDto.getEmployeeDto().getLastname())
						.email(ratingDto.getEmployeeDto().getEmail())
						.phone(ratingDto.getEmployeeDto().getPhone())
						.birthdate(ratingDto.getEmployeeDto().getBirthdate())
						.build())
				.customer(
					Customer.builder()
						.id(ratingDto.getCustomerDto().getId())
						.firstname(ratingDto.getCustomerDto().getFirstname())
						.lastname(ratingDto.getCustomerDto().getLastname())
						.email(ratingDto.getCustomerDto().getEmail())
						.phone(ratingDto.getCustomerDto().getPhone())
						.birthdate(ratingDto.getCustomerDto().getBirthdate())
						.build())
				.build();
	}
	
	
	
}













