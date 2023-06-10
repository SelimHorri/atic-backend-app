package tn.cita.app.config.annotation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import tn.cita.app.constant.AppConstants;

import java.lang.annotation.*;

@Target(value = {
		ElementType.ANNOTATION_TYPE, 
		ElementType.TYPE, 
		ElementType.FIELD, 
		ElementType.METHOD, 
		ElementType.PARAMETER
})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@JsonFormat(pattern = AppConstants.ZONED_DATE_TIME_FORMAT, shape = Shape.STRING)
@DateTimeFormat(pattern = AppConstants.ZONED_DATE_TIME_FORMAT)
@JsonSerialize(using = ZonedDateTimeSerializer.class)
public @interface ZonedDateTimeCustomFormat {
	
	String format() default AppConstants.ZONED_DATE_TIME_FORMAT;
	
}



