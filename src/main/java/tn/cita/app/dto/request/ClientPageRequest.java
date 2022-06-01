package tn.cita.app.dto.request;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.cita.app.constant.AppConstant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class ClientPageRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Builder.Default
	private int offset = 1;
	
	@Builder.Default
	private int size = AppConstant.PAGE_SIZE;
	
	private Sort sortBy;
	
	@Builder.Default
	private Sort.Direction sortDirection = Sort.Direction.DESC;
	
	public ClientPageRequest(final int offset, final int size) {
		this.offset = offset;
		this.size = size;
	}
	
	public ClientPageRequest(final Map<String, String> params) {
		this(Integer.parseInt(params.get("offset")), Integer.parseInt(params.get("size")));
	}
	
	
	
}















