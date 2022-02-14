package tn.cita.app.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;

import tn.cita.app.constant.AppConstant;

@Target(value = {ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@JsonFormat(pattern = AppConstant.INSTANT_FORMAT, shape = Shape.STRING)
@DateTimeFormat(pattern = AppConstant.INSTANT_FORMAT)
@JsonSerialize(using = InstantSerializer.class)
@JsonDeserialize(using = InstantDeserializer.class)
public @interface InstantCustomFormat {
	
	String format() default AppConstant.INSTANT_FORMAT;
	
}





