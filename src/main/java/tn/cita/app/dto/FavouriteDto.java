package tn.cita.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.config.annotation.LocalDateTimeCustomFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public final class FavouriteDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Input customerId should not be null")
	private Integer customerId;
	
	@NotNull(message = "Input saloonId should not be null")
	private Integer saloonId;
	
	@LocalDateTimeCustomFormat
	private LocalDateTime favouriteDate;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Input customer should not be null")
	private CustomerDto customerDto;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Input saloon should not be null")
	private SaloonDto saloonDto;
	
}












