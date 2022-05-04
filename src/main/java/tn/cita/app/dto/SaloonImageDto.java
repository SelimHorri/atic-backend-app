package tn.cita.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {})
@SuperBuilder
public final class SaloonImageDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String type;
	private Long size;
	private String imageLob;
	private Integer saloonId;
	
}










