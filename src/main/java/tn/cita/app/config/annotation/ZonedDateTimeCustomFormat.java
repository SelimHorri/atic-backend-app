package tn.cita.app.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import tn.cita.app.constant.AppConstant;

@Target(value = {ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@JsonFormat(pattern = AppConstant.ZONED_DATE_TIME_FORMAT, shape = Shape.STRING)
@DateTimeFormat(pattern = AppConstant.ZONED_DATE_TIME_FORMAT)
@JsonSerialize(using = ZonedDateTimeSerializer.class)
public @interface ZonedDateTimeCustomFormat {
	
	String format() default AppConstant.ZONED_DATE_TIME_FORMAT;
	
}





