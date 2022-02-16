package tn.cita.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.constant.AppConstant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public final class SaloonTagDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Input saloonId should not be null")
	private Integer saloonId;
	
	@NotNull(message = "Input tagId should not be null")
	private Integer tagId;
	
	@JsonFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime taggedDate;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Input saloon should not be null")
	private SaloonDto saloonDto;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Input tag should not be null")
	private TagDto tagDto;
	
}














