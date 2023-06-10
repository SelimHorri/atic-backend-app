package tn.cita.app.config.annotation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
@JsonFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT, shape = Shape.STRING)
@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
@JsonSerialize(using = LocalDateTimeSerializer.class)
@JsonDeserialize(using = LocalDateTimeDeserializer.class)
public @interface LocalDateTimeCustomFormat {
	
	String format() default AppConstants.LOCAL_DATE_TIME_FORMAT;
	
}



