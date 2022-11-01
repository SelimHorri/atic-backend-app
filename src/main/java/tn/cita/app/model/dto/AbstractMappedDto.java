package tn.cita.app.model.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class AbstractMappedDto extends AbstractAuditingMappedDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	
}






