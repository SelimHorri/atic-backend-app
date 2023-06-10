package tn.cita.app.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {})
@SuperBuilder
public final class SaloonImageDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = -2416618752965015845L;
	
	private String name;
	private String type;
	private Long size;
	private byte[] imageLob;
	
	@JsonProperty("saloon")
	private SaloonDto saloonDto;
	
}



