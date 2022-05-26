package tn.cita.app.dto.request;

import java.io.Serializable;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class ClientPageRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Builder.Default
	private final int offset = 1;
	
	@Builder.Default
	private final int size = AppConstant.PAGE_SIZE;
	
	private Sort sortBy;
	
	@Builder.Default
	private Sort.Direction direction = Sort.Direction.DESC;
	
}















