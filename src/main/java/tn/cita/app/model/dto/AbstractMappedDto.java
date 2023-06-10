package tn.cita.app.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class AbstractMappedDto extends AbstractAuditingMappedDto implements Serializable {
	
	private static final long serialVersionUID = 126696202570202128L;
	private Integer id;
	
}



