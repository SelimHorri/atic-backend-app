package tn.cita.app.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;

@Target(value = {
		ElementType.ANNOTATION_TYPE, 
		ElementType.TYPE, 
		ElementType.FIELD, 
		ElementType.METHOD, 
		ElementType.PARAMETER
})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@JsonFormat(shape = Shape.STRING)
@JsonSerialize(using = InstantSerializer.class)
@JsonDeserialize(using = InstantDeserializer.class)
public @interface InstantCustomFormat {
	
	String format();
	
}



