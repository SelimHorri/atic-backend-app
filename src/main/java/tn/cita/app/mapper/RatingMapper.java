package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Customer;
import tn.cita.app.domain.entity.Employee;
import tn.cita.app.domain.entity.Rating;
import tn.cita.app.dto.RatingDto;

public interface RatingMapper {
	
	public static RatingDto map(@NotNull final Rating rating) {
		return RatingDto.builder()
				.employeeId(rating.getEmployeeId())
				.customerId(rating.getCustomerId())
				.rateDate(rating.getRateDate())
				.rate(rating.getRate())
				.description(rating.getDescription())
				.employeeId(rating.getEmployeeId())
				.customerId(rating.getCustomerId())
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
						.id(ratingDto.getEmployeeId())
						.build())
				.customer(
					Customer.builder()
						.id(ratingDto.getCustomerId())
						.build())
				.build();
	}
	
	
	
}














